package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.*;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentForExtendingRentalRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentRequest;
import com.etiya.renACar.core.adapters.abstracts.BaseBankPaymentServiceAdapter;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.helpers.HelperMetods;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import com.etiya.renACar.repository.abstracts.PaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PaymentManager implements PaymentService {

    private PaymentRepository paymentRepository;
    private InvoiceService invoiceService;
    private RentalService rentalService;
    private OrderedAdditionalProductService orderedAdditionalProductService;
    private BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter;
    private UserService userService;
    private CarService carService;


    public PaymentManager(PaymentRepository paymentRepository, InvoiceService invoiceService,
                          RentalService rentalService, OrderedAdditionalProductService orderedAdditionalProductService,
                          @Qualifier("qnbfinansbank") BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter,
                          UserService userService,CarService carService) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
        this.rentalService = rentalService;
        this.orderedAdditionalProductService = orderedAdditionalProductService;
        this.baseBankPaymentServiceAdapter = baseBankPaymentServiceAdapter;
        this.userService = userService;
        this.carService = carService;
    }

    @Transactional
    @Override
    // kullanıcı araba kiralayabilmesi için sisteme kayıtlı olmalı, Yeni bir rental için kullanılacak sistemm
    public Result createPayment(CreatePaymentRequest createPaymentRequest) {

        Payment payment = new Payment();
        payment.setCreditCardNo(createPaymentRequest.getCreditCardNo());
        payment.setCardHolder(createPaymentRequest.getCardHolder());     // TODO: 4/26/2022 Credit Card'a taşı
        payment.setCvv(createPaymentRequest.getCvv());
        payment.setExpirationDate(createPaymentRequest.getExpirationDate());

        checkIfPaymentsuccess(payment); //Ödeme başarılı mı?

        var result = this.rentalService.add(createPaymentRequest.getCreateRentalRequest());//rental eklendi
        payment.setRental(result.getData()); //rentalId set edildi


        for (CreateOrderedAdditionalProductRequest c : createPaymentRequest.getOrderedAdditionalProductRequests()) {
            c.setRentalId(result.getData().getId());
            var result2 = this.orderedAdditionalProductService.add(c);
        }

        result.getData().setDailyPrice(this.calculateDailyPriceForRental(result.getData().getId()));//rentalDailyPrice

        User user = result.getData().getUser();
        payment.setUser(user);
        payment.setTotalPrice(this.calculateTotalPriceForPayment(payment.getRental().getId()));
        Payment paymentResult = this.paymentRepository.save(payment);

        CreateInvoiceRequest createInvoiceRequest = createPaymentRequest.getCreateInvoiceRequest();
        createInvoiceRequest.setRentalId(result.getData().getId());
        //createInvoiceRequest.setPaymentId(paymentResult.getId()); 
        this.invoiceService.add(createInvoiceRequest);

        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED_SUCCESSFULLY);

    }

    @Transactional
    @Override     //kiralama süresi uzatıldıgında kullanılıcak metot
    public Result createPaymentForExtendingRental(CreatePaymentForExtendingRentalRequest
                                                          createPaymentForExtendingRentalRequest) {

        Payment payment = new Payment();
        payment.setCreditCardNo(createPaymentForExtendingRentalRequest.getCreditCardNo());
        payment.setCardHolder(createPaymentForExtendingRentalRequest.getCardHolder());
        payment.setCvv(createPaymentForExtendingRentalRequest.getCvv());
        payment.setExpirationDate(createPaymentForExtendingRentalRequest.getExpirationDate());
        checkIfPaymentsuccess(payment);

        this.rentalService.updateDeliveryDateForExtendingRental(createPaymentForExtendingRentalRequest
                .getCreateRentalDeliveryDateRequest());

        CreateInvoiceRequest createInvoiceRequest = createPaymentForExtendingRentalRequest.getCreateInvoiceRequest();
        Rental rental = this.rentalService.getById(createPaymentForExtendingRentalRequest
                .getCreateRentalDeliveryDateRequest().getRentalId()).getData();

        createInvoiceRequest.setUserId(rental.getUser().getUserId());
        this.invoiceService.add(createInvoiceRequest);


        payment.setRental(rental);  //user swagger'da set edilmez //kullanıcı kaydı olmadan sistemden kiralamayapılamaz
        payment.setUser(rental.getUser());

        //payment.setTotalPrice(this.calculateTotalPriceForPayment(payment.getRental().getId()).getData());
        this.paymentRepository.save(payment);
        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED_SUCCESSFULLY);
    }

    //---------------------------Business Rules----------------------------------------------------
    private void checkIfPaymentsuccess(Payment payment) {
        if (!this.baseBankPaymentServiceAdapter.checkIfPaymentSuccess(payment)) {
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMENT_FAILED);
        }

    }

    private double calculateCheckIfReturnCityIsDifferentPrice(int rentalId) {
        if (this.rentalService.checkIfDeliveryCityisDifferentRentalCity(rentalId).isSuccess()) {
            return 750;
        } else
            return 0;
    }

    private double calculateDailyPriceForRental(int rentalId) {
       Rental rental =  this.rentalService.getById(rentalId).getData();
       double x = rental.getCar().getDailyPrice();
        double dailyPrice = HelperMetods.calculateForSum(this.orderedAdditionalProductService
                        .calculateOrderedAdditionalPrice(rentalId).getData(),x);

        System.out.println("--------------------------------------------");
        return dailyPrice;
    }

    private double calculateTotalPriceForPayment(int rentalId){
        double totalPrice = HelperMetods.calculateForSum(calculateCheckIfReturnCityIsDifferentPrice(rentalId),
                this.rentalService.calculateRentalPriceForCar(rentalId));//gün*dailyprice
        return totalPrice;
    }

}
