package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.BrandService;
import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseBrandDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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
    public void add(CreateBrandRequest createBrandRequest) {
//        Brand brand = new Brand();
//        brand.setName(createBrandRequest.getName()); //model veritabanı nesnesine çevrilir

        checkIfBrandNameExists(createBrandRequest.getName()) ;
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        this.brandRepository.save(brand);}

    @Override
    public List<ResponseBrandDto> getAll() {
        List<Brand>brands = this.brandRepository.findAll();
        List<ResponseBrandDto> response = brands.stream()
                .map(brand -> this.modelMapperService.forDto().map(brand, ResponseBrandDto.class))
                .collect(Collectors.toList());
        return response;
    }

    //---------------------------------business rules--------------------------

    private void checkIfBrandNameExists(String brandName){
        if(this.brandRepository.existsBrandByNameIgnoreCase(brandName)){
            throw new RuntimeException("This brand  already exists");
        }

    }
}
