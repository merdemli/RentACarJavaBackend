package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.AdditionalProductService;
import com.etiya.renACar.business.model.requests.createRequest.CreateAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateAdditionalProductRequest;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/additionalproducts")
public class AdditionalProductsController {

    private AdditionalProductService additionalProductService;

    public AdditionalProductsController(AdditionalProductService additionalProductService) {
        this.additionalProductService = additionalProductService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalProductRequest createAdditionalProductRequest) {
       return this.additionalProductService.add(createAdditionalProductRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalProductRequest deleteAdditionalProductRequest) {
        return this.additionalProductService.delete(deleteAdditionalProductRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalProductRequest updateAdditionalProductRequest) {
        return this.additionalProductService.update(updateAdditionalProductRequest);
    }
}
