package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponse;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private List<Car>cars;

    public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carRepository.save(car);
    }

    @Override
    public List<CarListResponse> getAll() {
        cars = this.carRepository.findAll();
        return map(cars);
    }

    @Override
    public List<CarListResponse> getAllByModelYear(int modelYear) {
        cars = this.carRepository.getByModelYear(modelYear);
        return map(cars);
    }

    @Override
    public List<CarListResponse> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
        cars = this.carRepository.findAll(pageable).getContent();
        return map(cars);
    }

    @Override
    public List<CarListResponse> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"modelYear");
        cars = this.carRepository.findAll(sort);
        return map(cars);
    }

    @Override
    public List<CarListResponse> getAllByModelYearIn(List<Integer>years) {
        cars = this.carRepository.getByModelYearIn(years);
        return map(cars);

    }

    @Override
    public List<CarListResponse> getAllByStatus(CarStates type) {
        cars = this.carRepository.getAllByStatus(type);
        return map(cars);
    }

    @Override
    public List<CarListResponse>getAllCarsById(int cityId) {
        cars= this.carRepository.getAllByCityId(cityId);
        return map(cars);
    }

    @Override
    public CarResponseDto getCarById(int carId) {
        Car car = this.carRepository.getCarById(carId);
        CarResponseDto dto = this.modelMapperService.forDto().map(car, CarResponseDto.class);
        return dto;
    }
/*
    @Override
    public Car getCarByIdAndStatus(int carId, CarStates type) {
        Car car = this.carRepository.getCarByIdAndStatus(carId,type);
        return car;
    }*/


    @Override
    public void updateMaintenanceStatus(int carId, CarStates type) {  //
        Car car = this.carRepository.getCarById(carId);
        car.setStatus(type);
        this.carRepository.save(car);
    }

    public void updateCarKmInfo(UpdateKmInfoRequest updateKmInfoRequest){
        Car car = this.carRepository.getCarById(updateKmInfoRequest.getCarId());
        car.setKmInfo(updateKmInfoRequest.getEndKm());
        this.carRepository.save(car);
    }

    private List<CarListResponse> map(List<Car> cars) {
        List<CarListResponse> dtos = cars.stream()//"stream of car" döner
                .map(car -> this.modelMapperService.forDto().map(car, CarListResponse.class))
                .collect(Collectors.toList());
        return dtos;

    }

    private Car map(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        //car.setId(createCarRequest.getId());
        return car;
    }


//----------------------------------------Business Rulse-----------------------

    @Override
    public boolean existsCarById(int carId) {
        return this.carRepository.existsById(carId); //bu metodu repoya yazmana geek yok
    }

}
