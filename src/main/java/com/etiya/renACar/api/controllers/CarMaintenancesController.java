package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.CarMaintenanceListResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carmaintenances")
public class CarMaintenancesController {


    private CarMaintenanceService carMaintenanceService;

    public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
        this.carMaintenanceService = carMaintenanceService;
    }

    @GetMapping("/getallmaintenancebycarid")
    public List<CarMaintenanceListResponseDto> getByCarId(@RequestParam int carId){
        return this.carMaintenanceService.getByCarId(carId);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest){
        this.carMaintenanceService.add( createCarMaintenanceRequest);
    }
}
