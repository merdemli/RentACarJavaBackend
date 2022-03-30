package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.CarMaintenanceListResponseDto;

import java.util.List;

public interface CarMaintenanceService {

    void add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

    List<CarMaintenanceListResponseDto> getByCarId(int carId);

}
