package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessDataResult;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {


    private RentalService rentalService;

    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest){
        return this.rentalService.add(createRentalRequest);
    }

    @PutMapping("/updateendkminfo")
    public Result updateEndKm(@RequestBody UpdateKmInfoRequest kmInfoRequest){
        return this.rentalService.UpdateEndKm(kmInfoRequest);
    }

    @GetMapping("/getbyid")
    public DataResult <Rental>getById(@RequestParam int rentalId){;

        return this.rentalService.getById(rentalId);
    }

}
