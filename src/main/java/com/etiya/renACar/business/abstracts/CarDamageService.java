package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDamageDto;
import com.etiya.renACar.model.entities.concretes.CarDamage;

import java.util.List;

public interface CarDamageService {

    void add(CreateCarDamageRequest createCarDamageRequest);
    List<ListCarDamageDto>getAll();
    List<ListCarDamageDto> getByCarId(int carId);
}
