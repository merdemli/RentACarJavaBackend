package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentForExtendingRentalRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreatePaymentRequest;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    //Result add(CreatePaymentRequest createPaymentRequest);

    Result createPayment(CreatePaymentRequest createPaymentRequest);
    Result createPaymentForExtendingRental(CreatePaymentForExtendingRentalRequest
                                                   createPaymentForExtendingRentalRequest);
}
