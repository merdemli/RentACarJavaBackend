package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.IndividualCustomerListResponse;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {

    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    DataResult<List<IndividualCustomerListResponse>> getAll();
}
