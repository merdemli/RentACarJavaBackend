package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateOrderedAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.ErrorResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.RentalRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private OrderedAdditionalProductService orderedAdditionalProductService;


    public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
                         CarService carService, OrderedAdditionalProductService orderedAdditionalProductService) {
        this.rentalRepository = rentalRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.orderedAdditionalProductService = orderedAdditionalProductService;

    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        checkIfCarAlreadyInRent(rental);

        this.rentalRepository.save(rental);
        this.carService.updateMaintenanceStatus(createRentalRequest.getCarId(),CarStates.rented);

        int rentalId= rental.getId();
        List<Integer> additionalProductIdList = createRentalRequest.getAdditionalProductIdList();
        this.orderedAdditionalProductService.addOrderedAdditionalProductForRental(rentalId,additionalProductIdList);

        return new SuccessResult(BusinessMessages.RentMessages.RENT_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest,Rental.class);
        this.rentalRepository.delete(rental);
        return new SuccessResult(BusinessMessages.RentMessages.RENT_DELETED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);
        this.rentalRepository.save(rental);
        return new SuccessResult(BusinessMessages.RentMessages.RENT_UPDATED_SUCCESSFULLY);
    }

    @Transactional
    public Result UpdateEndKm(UpdateKmInfoRequest kmInfoRequest){
        checkIfRentalExist(kmInfoRequest.getRentalId());
        Rental rental =this.rentalRepository.getById(kmInfoRequest.getRentalId());

        if(rental.getDeliveryDate().isEqual(LocalDate.now()) && !rental.isRentStatus()){
            this.rentalRepository.updateEndKmInfoForRentalTable(kmInfoRequest.getRentalId(),kmInfoRequest.getEndKm());
            this.carService.updateCarKmInfo(kmInfoRequest);
            this.carService.updateMaintenanceStatus(kmInfoRequest.getCarId(),CarStates.available);
        }
        return new SuccessResult(BusinessMessages.RentMessages.END_KILOMETER_INFO_UPDATED_SUCCESSFULLY);
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
//    public void checkIfCarisRented(int carId) {    //o arabanın rental tablosundaki durumu   !!!!!!!!!!!
//        if(this.rentalRepository.existsByCarId(carId)){//!!!!!!!!!!!!
//        for (Rental r : this.rentalRepository.getAllByCarId(carId)) {
//            if (r.isRentStatus()) { r.getCar().setStatus(CarStates.rented);
//                throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_RENT);
//            }}
//
//        }throw new BusinessException(BusinessMessages.CarMessages.CAR_NOT_FOUND);// Öyle bir Rental yok
//    }

    private void checkIfRentalExist(int rentalId){
        if(!this.rentalRepository.existsById(rentalId))
            throw new BusinessException(BusinessMessages.RentMessages.RENT_NOT_FOUND);
    }


//    @Override
//    public boolean existsByCarId(int carId) {    //!!!!
//        return this.rentalRepository.existsByCar_CarId(carId);
//    }
//

}
