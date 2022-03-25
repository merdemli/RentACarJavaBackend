package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.BrandService;
import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListBrandDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
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
    public void add(CreateBrandRequest createBrandRequest) {
//        Brand brand = new Brand();
//        brand.setName(createBrandRequest.getName()); //model veritabanı nesnesine çevrilir

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        this.brandRepository.save(brand);

    }

    @Override
    public List<ListBrandDto> getAll() {

        List<Brand>brands = this.brandRepository.findAll();
        List<ListBrandDto> response = brands.stream()
                .map(brand -> this.modelMapperService.forDto().map(brand,ListBrandDto.class))
                .collect(Collectors.toList());


        return response;
    }
}
