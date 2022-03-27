package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDamageDto;
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

    @GetMapping("/getall")
    public List<ResponseCarDamageDto> getAll() {
        return this.carDamageService.getAll();
    }

    @GetMapping("/getbycarid")
    public List<ResponseCarDamageDto> getByCarId(int carId){
        return this.carDamageService.getByCarId(carId);
    }

    @GetMapping("/getallpaged")
    public List<ResponseCarDamageDto>getAllPaged(@RequestParam(value ="pageNo") int pageNo,
                                                 @RequestParam(value="pageSize") int pageSize){
        return this.carDamageService.getAllPaged(pageNo,pageSize);
    }

    @GetMapping("/getallsorted")
    public List<ResponseCarDamageDto>getAllSorted(@RequestParam(value="sort") boolean sort,
                                                  @RequestParam(value="property") String property){
        return this.carDamageService.getAllSorted(sort,property);
    }
}
