package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.InvoiceService;
import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.abstracts.PaymentService;
import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentRequest;
import com.etiya.renACar.core.adapters.abstracts.BaseBankPaymentServiceAdapter;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.results.ErrorResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Payment;
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

    public PaymentManager(PaymentRepository paymentRepository, InvoiceService invoiceService,
                          RentalService rentalService, OrderedAdditionalProductService orderedAdditionalProductService,
                          @Qualifier("qnbfinansbank") BaseBankPaymentServiceAdapter baseBankPaymentServiceAdapter) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
        this.rentalService = rentalService;
        this.orderedAdditionalProductService = orderedAdditionalProductService;
        this.baseBankPaymentServiceAdapter = baseBankPaymentServiceAdapter;
    }

    @Transactional
    @Override
    public Result createPayment(CreatePaymentRequest createPaymentRequest) {


        Payment payment = new Payment();
        payment.setCreditCardNo(createPaymentRequest.getCreditCardNo());
        payment.setCardHolder(createPaymentRequest.getCardHolder());
        payment.setCvv(createPaymentRequest.getCvv());
        payment.setExpirationDate(createPaymentRequest.getExpirationDate());

        checkIfPaymentsuccess(payment);

        this.rentalService.add(createPaymentRequest.getCreateRentalRequest());
        for (CreateOrderedAdditionalProductRequest c : createPaymentRequest.getOrderedAdditionalProductRequests()) {
            this.orderedAdditionalProductService.add(c);
        }
        this.invoiceService.add(createPaymentRequest.getCreateInvoiceRequest());

        this.paymentRepository.save(payment);

        return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_ADDED_SUCCESSFULLY);


    }

    private void  checkIfPaymentsuccess(Payment payment){
        if (!this.baseBankPaymentServiceAdapter.checkIfPaymentSuccess(payment)) {
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMENT_FAILED);
        }

    }


}
