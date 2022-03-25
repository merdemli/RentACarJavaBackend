package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDamageDto;
import com.etiya.renACar.business.model.responses.listDtos.ListCarDto;
import com.etiya.renACar.model.entities.concretes.CarDamage;

import java.util.List;

public interface CarService {
    void add(CreateCarRequest createCarRequest);
    List<ListCarDto>getAll();

}
