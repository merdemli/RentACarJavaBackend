package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteCarDamageRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateCarDamageRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarDamageResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarDamageListResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.CarDamage;
import com.etiya.renACar.repository.abstracts.CarDamageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageRepository carDamageRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;

    private List<CarDamage>damages;

    public CarDamageManager(CarDamageRepository carDamageRepository, ModelMapperService modelMapperService,
                            CarService carService) {
        this.carDamageRepository = carDamageRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public void add(CreateCarDamageRequest createCarDamageRequest) {

       CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
       this.carDamageRepository.save(carDamage);
    }

    @Override
    public void update(UpdateCarDamageRequest updateCarDamageRequest) {
//        CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
//        this.carDamageRepository.save(carDamage);

        CarDamage carDamage = this.carDamageRepository.getById(updateCarDamageRequest.getId());
        carDamage.setDescription(updateCarDamageRequest.getDescription());
        carDamage.setCarDamageDate(updateCarDamageRequest.getCarDamageDate());
        this.carDamageRepository.save(carDamage);
    }

    @Override
    public void delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        this.carDamageRepository.deleteById(deleteCarDamageRequest.getId());
    }

    @Override
    public CarDamageResponseDto getyById(int carId) {
        CarDamage carDamage = this.carDamageRepository.getById(carId);
        CarDamageResponseDto dto = this.modelMapperService.forDto().map(carDamage, CarDamageResponseDto.class);
        return  dto;
    }

    @Override
    public List<CarDamageListResponseDto> getAll() {
        damages = this.carDamageRepository.findAll();
        return map(damages);
    }

    @Override
    public List<CarDamageListResponseDto> getByCarId(int carId) {
        damages = this.carDamageRepository.getByCarId(carId);
        return map(damages);

    }

    @Override                                     //String option
    public List<CarDamageListResponseDto> getAllSorted(boolean sort, String property) {
        //Sort sort = Sort.by(Sort.Direction.valueOf(option).toString().toUpperCase(),property);
        Sort sort1= Sort.by(checkSortDirectionType(sort),property);
        damages = this.carDamageRepository.findAll(sort1);
        return map(damages);
    }

    @Override
    public List<CarDamageListResponseDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
        damages  = this.carDamageRepository.findAll(pageable).getContent();
        return map(damages);
    }

    private List<CarDamageListResponseDto> map(List<CarDamage> damages){
        List<CarDamageListResponseDto>dtos =damages.stream()
                .map(carDamage -> this.modelMapperService.forDto().map(carDamage, CarDamageListResponseDto.class))
                .collect(Collectors.toList());
        return dtos;
    }


    //To.do utilities'e at
    public Sort.Direction checkSortDirectionType(boolean sort){
        if(sort) return Sort.Direction.ASC;
        else return Sort.Direction.DESC;
    }

    //-------------------------------Business Rules--------------------------------------------

    private boolean checkIfCarExists(int carId){
        return this.carService.existsCarById(carId);
        }



}
