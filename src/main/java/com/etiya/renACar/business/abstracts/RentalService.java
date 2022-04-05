package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.model.entities.concretes.Invoice;

import java.util.List;

public interface RentalService {

    Result add(CreateRentalRequest createRentalRequest);
    Result delete(DeleteRentalRequest deleteRentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result UpdateEndKm(UpdateKmInfoRequest kmInfoRequest);



    void checkIfCarisRented(int carId);
    boolean existsByCarId(int carId);
}
