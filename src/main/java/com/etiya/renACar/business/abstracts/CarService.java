package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponse;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStates;

import java.util.List;

public interface CarService {
    void add(CreateCarRequest createCarRequest);
    void updateStatus(int carId, CarStates type);
    void updateCarKmInfo(UpdateKmInfoRequest updateKmInfoRequest);

    List<CarListResponse>getAll();
    List<CarListResponse>getAllByModelYear(int modelYear);
    List<CarListResponse>getAllPaged(int pageNo, int pageSize); //dao'da findAll'dan gelir,findAll'a parametre geçilir
    List<CarListResponse>getAllSorted();
    List<CarListResponse>getAllByModelYearIn(List<Integer>years);
    List<CarListResponse>getAllByStatus(CarStates type);
    List<CarListResponse> getAllCarsById(int cityId);

    CarResponseDto getCarById(int carId);
    //Car  getCarByIdAndStatus(int carId, CarStates type);

    void checkIfCarInMaintenance(int carId); //CarRental'da kullanılacak
    boolean existsCarById(int carId);







}
