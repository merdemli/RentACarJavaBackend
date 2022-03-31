package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.IndividualCustomerService;
import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {

    @Autowired
    private IndividualCustomerService individualCustomerService;

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest){
        return this.individualCustomerService.add(createIndividualCustomerRequest);

    }


}
