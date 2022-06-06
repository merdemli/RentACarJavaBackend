package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateRentalDeliveryDateRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalForDailyPriceRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.model.entities.concretes.Rental;

public interface RentalService {

    DataResult<Rental>add(CreateRentalRequest createRentalRequest);
    Result delete(DeleteRentalRequest deleteRentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result UpdateEndKm(UpdateKmInfoRequest kmInfoRequest);
    Result updateDeliveryDateForExtendingRental(CreateRentalDeliveryDateRequest
                                                        createRentalDeliveryDateRequest);
    //Result createRentalForDailyPrice(CreateRentalForDailyPriceRequest updateRentalForDailyPriceRequest);

    DataResult <Rental>getById(int rentalId);


    boolean checkIfRentalExist(int rentalId);
    void checkIfCarisRented(int carId);
    Result checkIfDeliveryCityisDifferentRentalCity(int rentalId);
    double calculateRentalPriceForCar(int rentalId);
    //double calculateExtendingRentalPriceForCar(int rentalId);
    //boolean existsByCarId(int carId);
}
