package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.core.utilities.results.Result;

public interface IndividualCustomerService {

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
