package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateAdditionalProductRequest;
import com.etiya.renACar.core.utilities.results.Result;

public interface AdditionalProductService {
    Result add(CreateAdditionalProductRequest createAdditionalProductRequest) ;
    Result delete(DeleteAdditionalProductRequest deleteAdditionalProductRequest);
    Result update(UpdateAdditionalProductRequest updateAdditionalProductRequest);



}
