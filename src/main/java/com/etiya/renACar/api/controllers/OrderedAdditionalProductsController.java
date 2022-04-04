package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateOrderedAdditionalProductRequest;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderedadditionalproducts")
public class OrderedAdditionalProductsController {

    private OrderedAdditionalProductService orderedAdditionalProductService;

    public OrderedAdditionalProductsController(OrderedAdditionalProductService orderedAdditionalProductService) {
        this.orderedAdditionalProductService = orderedAdditionalProductService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateOrderedAdditionalProductRequest createOrderedAdditionalProductRequest) {
        return this.orderedAdditionalProductService.add(createOrderedAdditionalProductRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteOrderedAdditionalProductRequest deleteOrderedAdditionalProductRequest) {
        return this.orderedAdditionalProductService.delete(deleteOrderedAdditionalProductRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateOrderedAdditionalProductRequest updateOrderedAdditionalProductRequest) {
        return this.orderedAdditionalProductService.update(updateOrderedAdditionalProductRequest);
    }
}
