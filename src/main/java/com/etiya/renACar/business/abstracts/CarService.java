package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponseDto;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStates;

import java.util.List;

public interface CarService {
    void add(CreateCarRequest createCarRequest);
    void updateMaintenanceStatus(UpdateStatusForCarTableRequest updateCarForMaintanenceRequest);

    List<CarListResponseDto>getAll();
    List<CarListResponseDto>getAllByModelYear(int modelYear);
    List<CarListResponseDto>getAllPaged(int pageNo, int pageSize); //dao'da findAll'dan gelir,findAll'a parametre geçilir
    List<CarListResponseDto>getAllSorted();
    List<CarListResponseDto>getAllByModelYearIn(List<Integer>years);
    List<CarListResponseDto>getAllByStatus(CarStates type);

    CarResponseDto getCarById(int carId);
    Car  getCarByIdAndStatus(int carId, CarStates type);

    boolean existsCarById(int carId);




}
