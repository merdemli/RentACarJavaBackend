package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteCarDamageRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateCarDamageRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarDamageResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarDamageListResponseDto;

import java.util.List;

public interface CarDamageService {

    CarDamageResponseDto getyById(int carId);

    void add(CreateCarDamageRequest createCarDamageRequest);
    void update(UpdateCarDamageRequest updateCarDamageRequest);
    void delete(DeleteCarDamageRequest deleteCarDamageRequest);

    List<CarDamageListResponseDto> getByCarId(int carId);
    List<CarDamageListResponseDto>getAll();
    List<CarDamageListResponseDto>getAllSorted(boolean sort, String property);//dao'da findAll'dan gelir,findAll'a parametre geçilir
    List<CarDamageListResponseDto>getAllPaged(int pageNo, int pageSize);
}
