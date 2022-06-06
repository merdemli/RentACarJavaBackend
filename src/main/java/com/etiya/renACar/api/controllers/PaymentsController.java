package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.PaymentService;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentForExtendingRentalRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentRequest;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/createpayment")
    public Result createPayment(@RequestBody CreatePaymentRequest createPaymentRequest){
        return this.paymentService.createPayment(createPaymentRequest);
    }

    @PostMapping("/createpaymentforextendingrental")
    public Result createPaymentForExtendingRental(@RequestBody CreatePaymentForExtendingRentalRequest
                                                          createPaymentForExtendingRentalRequest){
        return this.paymentService.createPaymentForExtendingRental(createPaymentForExtendingRentalRequest);
    }

}
