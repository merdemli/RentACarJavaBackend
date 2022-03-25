package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListBrandDto;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarRequest createCarRequest){
        this.carService.add(createCarRequest);
    }

    @GetMapping("/getAll")
    public List<ListCarDto>getAll(){
        return this.carService.getAll();

    }


}
