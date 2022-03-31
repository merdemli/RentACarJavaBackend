package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponseDto;
import com.etiya.renACar.model.enums.CarStates;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateCarRequest createCarRequest){
        this.carService.add(createCarRequest);
    }

    @GetMapping("/getall")
    public List<CarListResponseDto>getAll(){
        return this.carService.getAll();
    }

    @GetMapping("/getallbymodelyear")
    public List<CarListResponseDto>getAllByModelYear(@RequestParam("model_year") int modelYear){
        return this.carService.getAllByModelYear(modelYear);
        // requestParam:kullanıcıdan parametreyi isteğin sekiilde alabilmeni sağlar veya bize o şekilde gönderilir
    }

    @GetMapping("/getallpaged")
    public List<CarListResponseDto>getAllPaged(int pageNo, int pageSize){
        return this.carService.getAllPaged(pageNo,pageSize);
    } //dao'da findAll'dan gelir,findAll'a parametre geçilir

    @GetMapping("/getallsorted")
    public List<CarListResponseDto>getAllSorted(){
        return this.carService.getAllSorted();
    }

    @GetMapping("/getallbymodelyearin")
    public List<CarListResponseDto> getAllByModelYearIn(@RequestParam List<Integer>years){
        return this.carService.getAllByModelYearIn(years);
    }

    @GetMapping("/getallbystatus")
    public List<CarListResponseDto> getAllByStatus(@RequestParam CarStates type){
        return this.carService.getAllByStatus(type);
    }

    @GetMapping("/getcarbyid")
   public CarResponseDto getCarById(@RequestParam int carId) {
        return this.carService.getCarById(carId);
   }


   @PutMapping("/updatecarformaintenancestatus")
    public void updateMaintenanceStatus(UpdateStatusForCarTableRequest updateCarForMaintanenceRequest){
        this.carService.updateMaintenanceStatus(updateCarForMaintanenceRequest);
   }




}
