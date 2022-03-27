package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.Car;
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
    public List<ResponseCarDto> getAll() {
        List<Car> cars = this.carRepository.findAll();
        return map(cars);
    }

    @Override
    public List<ResponseCarDto> getAllByModelYear(int modelYear) {
        List<Car>cars = this.carRepository.getByModelYear(modelYear);
        return map(cars);
    }

    @Override
    public List<ResponseCarDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
        List<Car>cars = this.carRepository.findAll(pageable).getContent();
        return map(cars);
    }

    @Override
    public List<ResponseCarDto> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"modelYear");
        List<Car>cars = this.carRepository.findAll(sort);
        return map(cars);
    }

    @Override
    public List<ResponseCarDto> getAllByModelYearIn(List<Integer>years) {
        List<Car>cars = this.carRepository.getByModelYearIn(years);
        return map(cars);

    }

    private List<ResponseCarDto> map(List<Car> cars) {
        List<ResponseCarDto> dtos = cars.stream()//"stream of car" döner
                .map(car -> this.modelMapperService.forDto().map(car, ResponseCarDto.class))
                .collect(Collectors.toList());
        return dtos;

    }
}
