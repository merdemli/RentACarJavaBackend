package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateOrderedAdditionalProductRequest;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.repository.abstracts.OrderedAdditionalProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedAdditionalProductManager implements OrderedAdditionalProductService {

    private OrderedAdditionalProductRepository orderedAdditionalProductRepository;
    private ModelMapperService modelMapperService;

    public OrderedAdditionalProductManager(OrderedAdditionalProductRepository orderedAdditionalProductRepository, ModelMapperService modelMapperService) {
        this.orderedAdditionalProductRepository = orderedAdditionalProductRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateOrderedAdditionalProductRequest createOrderedAdditionalProductRequest) {
        OrderedAdditionalProduct orderedAdditionalProduct = this.modelMapperService.forRequest()
                .map(createOrderedAdditionalProductRequest, OrderedAdditionalProduct.class);
       //To.Do orderedAdditionalProduct.setTotalPrice(); Total Price Db'ye yazdırma ?????
        this.orderedAdditionalProductRepository.save(orderedAdditionalProduct);
        return new SuccessResult(BusinessMessages
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


    @Override
    public void addOrderedAdditionalProductForRental(int rentalId, List<Integer> additionalProductIdList) {
        CreateOrderedAdditionalProductRequest r = new CreateOrderedAdditionalProductRequest();

        for(int additionalProductId :additionalProductIdList ){
            //int orderedAdditionalProductId = additionalProductId;
            r.setAdditionalProductId(additionalProductId);
            r.setRentalId(rentalId);
            add(r);
        }

    }


}
