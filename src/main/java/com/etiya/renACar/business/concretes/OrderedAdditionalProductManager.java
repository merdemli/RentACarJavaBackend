package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.AdditionalProductService;
import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateOrderedAdditionalProductRequest;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.helpers.HelperMetods;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.*;
import com.etiya.renACar.model.entities.concretes.AdditionalProduct;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.repository.abstracts.OrderedAdditionalProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedAdditionalProductManager implements OrderedAdditionalProductService {

    private OrderedAdditionalProductRepository orderedAdditionalProductRepository;
    private ModelMapperService modelMapperService;
    private AdditionalProductService additionalProductService;

    public OrderedAdditionalProductManager(OrderedAdditionalProductRepository orderedAdditionalProductRepository, AdditionalProductService additionalProductService,ModelMapperService modelMapperService) {
        this.orderedAdditionalProductRepository = orderedAdditionalProductRepository;
        this.modelMapperService = modelMapperService;
        this.additionalProductService = additionalProductService;
    }

    @Override
    public DataResult<OrderedAdditionalProduct> add(CreateOrderedAdditionalProductRequest createOrderedAdditionalProductRequest) {
        OrderedAdditionalProduct orderedAdditionalProduct = this.modelMapperService.forRequest()
                .map(createOrderedAdditionalProductRequest, OrderedAdditionalProduct.class);

        AdditionalProduct ap= this.additionalProductService.getById(createOrderedAdditionalProductRequest.getAdditionalProductId());
        double price= HelperMetods.calculateForMult(ap.getUnitPrice(),orderedAdditionalProduct.getAmount());
        orderedAdditionalProduct.setTotalPrice(price);

        orderedAdditionalProduct= this.orderedAdditionalProductRepository.save(orderedAdditionalProduct);
        return new SuccessDataResult<OrderedAdditionalProduct>(orderedAdditionalProduct,BusinessMessages
                .OrderedAdditionalProductMessages.ORDERED_ADDITIONAL_PRODUCT_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteOrderedAdditionalProductRequest deleteOrderedAdditionalProductRequest) {
        OrderedAdditionalProduct orderedAdditionalProduct = this.modelMapperService.forRequest()
                .map(deleteOrderedAdditionalProductRequest, OrderedAdditionalProduct.class);

        this.orderedAdditionalProductRepository.delete(orderedAdditionalProduct);
        return new SuccessResult(BusinessMessages
                .OrderedAdditionalProductMessages.ORDERED_ADDITIONAL_PRODUCT_DELETED_SUCCESSFULLY);}

    @Override
    public Result update(UpdateOrderedAdditionalProductRequest updateOrderedAdditionalProductRequest) {
        OrderedAdditionalProduct orderedAdditionalProduct = this.modelMapperService.forRequest()
                .map(updateOrderedAdditionalProductRequest, OrderedAdditionalProduct.class);
        this.orderedAdditionalProductRepository.save(orderedAdditionalProduct);
        return new SuccessResult(BusinessMessages
                .OrderedAdditionalProductMessages.ORDERED_ADDITIONAL_PRODUCT_UPDATED_SUCCESSFULLY);

    }

    public DataResult<Double>calculateOrderedAdditionalPrice(int rentalId){

        checkIfRentExists(rentalId);///
        List<OrderedAdditionalProduct> products = this.orderedAdditionalProductRepository.getAllByRental_Id(rentalId);

        double dailyTotalPrice = 0;
        for (OrderedAdditionalProduct p : products){
            dailyTotalPrice += p.getTotalPrice();
        }
        return new SuccessDataResult<Double>(dailyTotalPrice,BusinessMessages
                .OrderedAdditionalProductMessages.ORDERED_ADDITIONAL_PRODUCT_PRICE_CALCULATED_SUCCESSFULLY);

    }
    //------------------------BusinessRules------------------------------------

    private void  checkIfRentExists(int rentalId){ /////
        if(!this.orderedAdditionalProductRepository.existsByRental_Id(rentalId)){
            throw new BusinessException(BusinessMessages.OrderedAdditionalProductMessages
                    .RENT_NOT_EXISTS_IN_ORDERED_ADDITIONAL_PRODUCT);
        }
    }
}
