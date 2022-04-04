package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.repository.abstracts.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @Autowired
    private RentalService rentalService;


    /*//public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }*/

    @PostMapping("/add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest){
        return this.rentalService.add(createRentalRequest);

    }

    @GetMapping("/ boolean existsByCarId(int carId);")
    public boolean existsByCarId(@RequestParam int carId){
        return this.rentalService.existsByCarId(carId);
    }

    @PutMapping("/updateendkminfo")
    Result UpdateEndKm(@RequestBody UpdateKmInfoRequest kmInfoRequest){
        return this.rentalService.UpdateEndKm(kmInfoRequest);

    }

}
