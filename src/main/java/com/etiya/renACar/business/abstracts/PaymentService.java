package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentRequest;
import com.etiya.renACar.core.utilities.results.Result;

public interface PaymentService {

    //Result add(CreatePaymentRequest createPaymentRequest);

    Result createPayment(CreatePaymentRequest createPaymentRequest);
}
