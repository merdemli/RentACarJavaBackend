package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDamageDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.CarDamage;
import com.etiya.renACar.repository.abstracts.CarDamageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageRepository carDamageRepository;
    private ModelMapperService modelMapperService;

    public CarDamageManager(CarDamageRepository carDamageRepository, ModelMapperService modelMapperService) {
        this.carDamageRepository = carDamageRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateCarDamageRequest createCarDamageRequest) {

       CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
       this.carDamageRepository.save(carDamage);
    }

    @Override
    public List<ListCarDamageDto> getAll() {
        List<CarDamage>damages = this.carDamageRepository.findAll();
        List<ListCarDamageDto>response = damages.stream()
                .map(carDamage -> this.modelMapperService.forDto().map(carDamage,ListCarDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListCarDamageDto> getByCarId(int carId) {

        List<CarDamage> carDamages = this.carDamageRepository.getByCarId(carId);
        List<ListCarDamageDto> response =  carDamages.stream()
                .map(carDamage -> this.modelMapperService.forDto().map(carDamage,ListCarDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }

}
