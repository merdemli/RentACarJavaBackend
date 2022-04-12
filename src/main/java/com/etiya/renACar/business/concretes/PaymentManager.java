package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.*;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.*;
import com.etiya.renACar.core.adapters.abstracts.BaseBankPaymentServiceAdapter;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.results.ErrorResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.IndividualCustomer;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import com.etiya.renACar.repository.abstracts.PaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentManager implements PaymentService {

    private PaymentRepository paymentRepository;
    private InvoiceService invoiceService;
    private RentalService rentalService;
    private OrderedAdditionalProductService orderedAdditionalProductService;
    private BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter;
    private UserService userService;

    public PaymentManager(PaymentRepository paymentRepository, InvoiceService invoiceService,
                          RentalService rentalService, OrderedAdditionalProductService orderedAdditionalProductService,
                          @Qualifier("qnbfinansbank") BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter,
                          UserService userService) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
        this.rentalService = rentalService;
        this.orderedAdditionalProductService = orderedAdditionalProductService;
        this.baseBankPaymentServiceAdapter = baseBankPaymentServiceAdapter;
        this.userService = userService;

    }

    @Transactional
    @Override   // kullanıcı araba kiralayabilmesi için sisteme kayıtlı olmalı, Yeni bir rental için kullanılacak sistemm
    public Result createPayment(CreatePaymentRequest createPaymentRequest) {

        Payment payment = new Payment();
        payment.setCreditCardNo(createPaymentRequest.getCreditCardNo());
        payment.setCardHolder(createPaymentRequest.getCardHolder());
        payment.setCvv(createPaymentRequest.getCvv());
        payment.setExpirationDate(createPaymentRequest.getExpirationDate());

        checkIfPaymentsuccess(payment);

        var result = this.rentalService.add(createPaymentRequest.getCreateRentalRequest());//rental eklendi
        payment.setRental(result.getData()); 

        for (CreateOrderedAdditionalProductRequest c : createPaymentRequest.getOrderedAdditionalProductRequests()) {
            c.setRentalId(result.getData().getId()); 
            this.orderedAdditionalProductService.add(c);
        }

        User user = result.getData().getUser();
        payment.setUser(user);
        Payment paymentResult = this.paymentRepository.save(payment);

        CreateInvoiceRequest createInvoiceRequest = createPaymentRequest.getCreateInvoiceRequest();
        createInvoiceRequest.setRentalId(result.getData().getId());
        //createInvoiceRequest.setPaymentId(paymentResult.getId()); 
        this.invoiceService.add(createInvoiceRequest);

        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED_SUCCESSFULLY);

    }

    @Transactional
    @Override
    public Result createPaymentForExtendingRental(CreatePaymentForExtendingRentalRequest
                                                              createPaymentForExtendingRentalRequest) {

        Payment payment = new Payment();
        payment.setCreditCardNo(createPaymentForExtendingRentalRequest.getCreditCardNo());
        payment.setCardHolder(createPaymentForExtendingRentalRequest.getCardHolder());
        payment.setCvv(createPaymentForExtendingRentalRequest.getCvv());
        payment.setExpirationDate(createPaymentForExtendingRentalRequest.getExpirationDate());
        checkIfPaymentsuccess(payment);

        this.rentalService.updateDeliveryDateForExtendingRental(createPaymentForExtendingRentalRequest.getCreateRentalDeliveryDateRequest());


        CreateInvoiceRequest createInvoiceRequest = createPaymentForExtendingRentalRequest.getCreateInvoiceRequest();
        this.invoiceService.add(createInvoiceRequest);
        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED_SUCCESSFULLY);
    }

//---------------------------Business Rules----------------------------------------------------
    private void  checkIfPaymentsuccess(Payment payment){
        if (!this.baseBankPaymentServiceAdapter.checkIfPaymentSuccess(payment)) {
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMENT_FAILED);
        }

    }


}
