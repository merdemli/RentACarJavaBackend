package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateRentalDeliveryDateRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;

public interface RentalService {

    DataResult<Rental>add(CreateRentalRequest createRentalRequest);
    Result delete(DeleteRentalRequest deleteRentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result UpdateEndKm(UpdateKmInfoRequest kmInfoRequest);
    Result updateDeliveryDateForExtendingRental(CreateRentalDeliveryDateRequest
                                                        createRentalDeliveryDateRequest);

    Rental getById(int rentalId);



    boolean checkIfRentalExist(int rentalId);
    void checkIfCarisRented(int carId);
    //boolean existsByCarId(int carId);
}
