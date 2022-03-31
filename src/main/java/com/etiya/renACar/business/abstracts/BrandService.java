package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.BrandListResponseDto;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;

import java.util.List;

public interface BrandService {

    Result add(CreateBrandRequest createBrandRequest);//void add();
    DataResult<List<BrandListResponseDto>>getAll();    //List<BrandListResponseDto>>getAll()
}
