package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDamageDto;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.CarDamage;
import com.etiya.renACar.repository.abstracts.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;

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
    public List<ListCarDto> getAll() {

        List<Car> cars = this.carRepository.findAll();
        List<ListCarDto> response = cars.stream().map(car -> this.modelMapperService
                        .forDto().map(car, ListCarDto.class))
                         .collect(Collectors.toList());
        return response;
    }


}
