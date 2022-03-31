package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateCarDamageRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.CarDamageListResponseDto;
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

    @PutMapping("/update")
    public void update(@RequestBody UpdateCarDamageRequest updateCarDamageRequest){
        this.carDamageService.update(updateCarDamageRequest);
    }

    @GetMapping("/getall")
    public List<CarDamageListResponseDto> getAll() {
        return this.carDamageService.getAll();
    }

    @GetMapping("/getbycarid")
    public List<CarDamageListResponseDto> getByCarId(int carId){
        return this.carDamageService.getByCarId(carId);
    }

    @GetMapping("/getallpaged")
    public List<CarDamageListResponseDto>getAllPaged(@RequestParam(value ="pageNo") int pageNo,
                                                     @RequestParam(value="pageSize") int pageSize){
        return this.carDamageService.getAllPaged(pageNo,pageSize);
    }

    @GetMapping("/getallsorted")
    public List<CarDamageListResponseDto>getAllSorted(@RequestParam(value="sort") boolean sort,
                                                      @RequestParam(value="property") String property){
        return this.carDamageService.getAllSorted(sort,property);
    }
}
