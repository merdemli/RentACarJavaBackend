package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.AdditionalProductService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateAdditionalProductRequest;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.AdditionalProduct;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.repository.abstracts.AdditionalProductRepository;
import org.springframework.stereotype.Service;

@Service
public class AdditionalProductManager implements AdditionalProductService {

    private AdditionalProductRepository additionalProductRepository;
    private ModelMapperService modelMapperService;

    public AdditionalProductManager(AdditionalProductRepository additionalProductRepository, ModelMapperService modelMapperService) {
        this.additionalProductRepository = additionalProductRepository;
        this.modelMapperService= modelMapperService;
    }

    @Override
    public Result add(CreateAdditionalProductRequest createAdditionalProductRequest) {
        AdditionalProduct additionalProduct = this.modelMapperService.forRequest()
                .map(createAdditionalProductRequest, AdditionalProduct.class);

        this.additionalProductRepository.save(additionalProduct);
        return new SuccessResult(BusinessMessages.AdditionalProductMessages.ADDITIONAL_PRODUCT_ADDED_SUCCESSFULLY);
    }


    @Override
    public Result delete(DeleteAdditionalProductRequest deleteAdditionalProductRequest) {
        AdditionalProduct additionalProduct = this.modelMapperService.forRequest()
                .map(deleteAdditionalProductRequest, AdditionalProduct.class);
        this.additionalProductRepository.delete(additionalProduct);
        return new SuccessResult(BusinessMessages.AdditionalProductMessages.ADDITIONAL_PRODUCT_DELETED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateAdditionalProductRequest updateAdditionalProductRequest) {
        AdditionalProduct additionalProduct = this.modelMapperService.forRequest()
                .map(updateAdditionalProductRequest, AdditionalProduct.class);
        this.additionalProductRepository.save(additionalProduct);
        return new SuccessResult(BusinessMessages.AdditionalProductMessages.ADDITIONAL_PRODUCT_UPDATED_SUCCESSFULLY);

    }

    @Override
    public AdditionalProduct getById(int additionalProductId) {

        return this.additionalProductRepository.getById(additionalProductId);

    }
}
