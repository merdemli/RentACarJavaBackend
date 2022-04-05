package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.CarMaintenanceListResponse;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.model.entities.concretes.CarMaintenance;

import java.util.List;

public interface CarMaintenanceService {

    Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);

    List<CarMaintenanceListResponse> getByCarId(int carId);





}
