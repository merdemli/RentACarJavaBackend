package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarMaintenanceDto;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.CarMaintenance;

import java.util.List;

public interface CarMaintenanceService {

    void add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
    List<ResponseCarMaintenanceDto> getByCarId(int carId);

}
