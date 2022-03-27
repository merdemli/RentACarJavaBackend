package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarDto;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStateStatus;

import java.util.List;

public interface CarService {
    void add(CreateCarRequest createCarRequest);
    List<ResponseCarDto>getAll();
    List<ResponseCarDto>getAllByModelYear(int modelYear);

    List<ResponseCarDto>getAllPaged(int pageNo, int pageSize); //dao'da findAll'dan gelir,findAll'a parametre geçilir
    List<ResponseCarDto>getAllSorted();
    List<ResponseCarDto>getAllByModelYearIn(List<Integer>years);
    List<ResponseCarDto>getAllByStatus(CarStateStatus type);

}
