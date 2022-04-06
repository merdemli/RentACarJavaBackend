package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateOrderedAdditionalProductRequest;
import com.etiya.renACar.core.utilities.results.Result;

import java.util.List;

public interface OrderedAdditionalProductService {

    Result add(CreateOrderedAdditionalProductRequest createOrderedAdditionalProductRequest);
    Result delete(DeleteOrderedAdditionalProductRequest deleteOrderedAdditionalProductRequest);
    Result update(UpdateOrderedAdditionalProductRequest updateOrderedAdditionalProductRequest);
    //rentalda ek hizmetler ekleme için kullanılacak
}
