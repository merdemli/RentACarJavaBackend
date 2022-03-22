package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.request.createRequest.CreateBrandRequest;

public interface BrandService {

    void add(CreateBrandRequest createBrandRequest);
}
