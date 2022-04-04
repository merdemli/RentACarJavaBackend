package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.ErrorResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;


    public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService, CarService carService) {
        this.rentalRepository = rentalRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        checkIfCarAlreadyInRent(rental);

        this.rentalRepository.save(rental);
        this.carService.updateMaintenanceStatus(createRentalRequest.getCarId(),CarStates.rented);

        return new SuccessResult(BusinessMessages.RentMessages.RENT_ADDED_SUCCESSFULLY);
    }

    //-----------Business Rules--------------------------------------------------------------


    private Result checkIfCarAlreadyInRent(Rental rental) {

        //checkIfCarisRented(rental.getCar().getId());
        checkIfDeliveryDate(rental.getDeliveryDate());
        checkIfCarisAlreadyisRentedWithState(rental.getCar().getId());
        return new SuccessResult(BusinessMessages.RentMessages.CAR_NOT_IN_RENT);

    }

    private void checkIfDeliveryDate(LocalDate deliveryDate) {
        if (deliveryDate == null) throw new BusinessException("tesim tarihi boş bırakılamaz");
        if (deliveryDate.isBefore(LocalDate.now())) {
            throw new BusinessException("teslim tarihi uygun değildir");
        }
    }

    private void checkIfCarisAlreadyisRentedWithState(int carId) {
        CarResponseDto car1 = this.carService.getCarById(carId);
        if (car1.getStatus() == CarStates.rented)                          //1 CarTable'dan
            throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_RENT);
    }

    //true: kirada false: uygun To.Do,Entity
    public void checkIfCarisRented(int carId) {    //o arabanın rental tablosundaki durumu   !!!!!!!!!!!
        if(this.rentalRepository.existsRentalByCar_Id(carId)){//!!!!!!!!!!!!
        for (Rental r : this.rentalRepository.getAllByCarId(carId)) {
            if (r.isRentStatus()) { r.getCar().setStatus(CarStates.rented);
                throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_RENT);
            }}

        }throw new BusinessException(BusinessMessages.CarMessages.CAR_NOT_FOUND);// Öyle bir Rental yok
    }

    @Override
    public boolean existsByCarId(int carId) {    //!!!!
        return this.rentalRepository.existsRentalByCar_Id(carId);
    }


}
