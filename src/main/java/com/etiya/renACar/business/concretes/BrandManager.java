package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.BrandService;
import com.etiya.renACar.business.model.request.createRequest.CreateBrandRequest;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;

    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {

        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName()); //model veritabanı nesnesine çevrilir
        this.brandRepository.save(brand);

    }
}
