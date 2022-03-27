package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarDamageRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDamageDto;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDto;

import java.util.List;

public interface CarDamageService {

    void add(CreateCarDamageRequest createCarDamageRequest);
    List<ResponseCarDamageDto>getAll();
    List<ResponseCarDamageDto> getByCarId(int carId);
    List<ResponseCarDamageDto>getAllSorted(boolean sort,String property);//dao'da findAll'dan gelir,findAll'a parametre geçilir
    List<ResponseCarDamageDto>getAllPaged(int pageNo,int pageSize);
}
