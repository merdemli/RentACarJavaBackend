package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.BrandListResponseDto;

import java.util.List;

public interface BrandService {

    void add(CreateBrandRequest createBrandRequest);

    List<BrandListResponseDto>getAll();
}
