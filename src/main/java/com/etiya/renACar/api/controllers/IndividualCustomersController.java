package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.IndividualCustomerService;
import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.IndividualCustomerListResponse;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {

    @Autowired
    private IndividualCustomerService individualCustomerService;

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest){
        return this.individualCustomerService.add(createIndividualCustomerRequest);

    }

    @GetMapping("getall")
    DataResult<List<IndividualCustomerListResponse>> getAll(){
        return this.individualCustomerService.getAll();
    }


}
