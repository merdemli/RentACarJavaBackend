package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.BrandService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.BrandListResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessDataResult;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
        this.brandRepository = brandRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
//        Brand brand = new Brand();
//        brand.setName(createBrandRequest.getName()); //model veritabanı nesnesine çevrilir

        if (checkIfBrandNameExists(createBrandRequest.getName()))
            throw new BusinessException(BusinessMessages.BrandMessages.BRAND_ALREADY_EXISTS);
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandRepository.save(brand);
        return new SuccessResult(BusinessMessages.BrandMessages.BRAND_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<List<BrandListResponseDto>>getAll() {
        List<Brand> brands = this.brandRepository.findAll();
        List<BrandListResponseDto> response = brands.stream()
                .map(brand -> this.modelMapperService.forDto().map(brand, BrandListResponseDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, BusinessMessages.BrandMessages.BRANDS_LISTED_SUCCESSFULLY);
    }


    //---------------------------------business rules--------------------------

    private boolean checkIfBrandNameExists(String brandName) {
        return this.brandRepository.existsBrandByNameIgnoreCase(brandName);
    }
}
