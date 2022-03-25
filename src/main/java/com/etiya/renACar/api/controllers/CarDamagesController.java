package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDamageDto;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carDamages")
public class CarDamagesController {

    private CarDamageService carDamageService;

    public CarDamagesController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarDamageRequest createCarDamageRequest){
        this.carDamageService.add(createCarDamageRequest);
    }

    @GetMapping("/getAll")
    public List<ListCarDamageDto> getAll() {
        return this.carDamageService.getAll();
    }
    @GetMapping("/getByCarId")
    public List<ListCarDamageDto> getByCarId(int carId){
        return this.carDamageService.getByCarId(carId);
    }
}
