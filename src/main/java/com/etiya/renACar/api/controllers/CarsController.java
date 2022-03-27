package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDto;
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

    @GetMapping("/getall")
    public List<ResponseCarDto>getAll(){
        return this.carService.getAll();
    }

    @GetMapping("/getallbymodelyear")
    public List<ResponseCarDto>getAllByModelYear(@RequestParam("model_year") int modelYear){
        return this.carService.getAllByModelYear(modelYear);
        // requestParam:kullanıcıdan parametreyi isteğin sekiilde alabilmeni sağlar veya bize o şekilde gönderilir
    }

    @GetMapping("/getallpaged")
    public List<ResponseCarDto>getAllPaged(int pageNo, int pageSize){
        return this.carService.getAllPaged(pageNo,pageSize);
    } //dao'da findAll'dan gelir,findAll'a parametre geçilir

    @GetMapping("/getallsorted")
    public List<ResponseCarDto>getAllSorted(){
        return this.carService.getAllSorted();
    }

    @GetMapping("getallbymodelyearin")
    public List<ResponseCarDto> getAllByModelYearIn(@RequestParam List<Integer>years){
        return this.carService.getAllByModelYearIn(years);
    }
}
