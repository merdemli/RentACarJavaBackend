package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarDamageService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDamageDto;
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

    private List<CarDamage>damages;

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
    public List<ResponseCarDamageDto> getAll() {
        damages = this.carDamageRepository.findAll();
        return map(damages);
    }

    @Override
    public List<ResponseCarDamageDto> getByCarId(int carId) {
        damages = this.carDamageRepository.getByCarId(carId);
        return map(damages);

    }

    @Override                                     //String option
    public List<ResponseCarDamageDto> getAllSorted(boolean sort,String property) {
        //Sort sort = Sort.by(Sort.Direction.valueOf(option),property);
        Sort sort1= Sort.by(checkSortDirectionType(sort),property);
        damages = this.carDamageRepository.findAll(sort1);
        return map(damages);
    }

    @Override
    public List<ResponseCarDamageDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNo-1, pageSize);
        damages  = this.carDamageRepository.findAll(pageable).getContent();
        return map(damages);
    }

    private List<ResponseCarDamageDto> map(List<CarDamage> damages){
        List<ResponseCarDamageDto>dtos =damages.stream()
                .map(carDamage -> this.modelMapperService.forDto().map(carDamage, ResponseCarDamageDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

    public Sort.Direction checkSortDirectionType(boolean sort){
        if(sort) return Sort.Direction.ASC;
        else return Sort.Direction.DESC;
    }

}
