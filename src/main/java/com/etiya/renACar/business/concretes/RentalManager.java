package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.abstracts.OrderedAdditionalProductService;
import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalDeliveryDateRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateRentalRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateKmInfoRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.*;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.RentalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private OrderedAdditionalProductService orderedAdditionalProductService;


    public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
                         CarService carService, OrderedAdditionalProductService orderedAdditionalProductService
    ) {
        this.rentalRepository = rentalRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public DataResult<Rental> add(CreateRentalRequest createRentalRequest) {

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

        checkIfCarAlreadyInRent(rental); //araba kirada mı
        this.carService.checkIfCarInMaintenance(createRentalRequest.getCarId()); //araba bakımda mı

        rental = this.rentalRepository.save(rental);

        this.carService.updateStatus(createRentalRequest.getCarId(), CarStates.rented);

        return new SuccessDataResult<Rental>(rental, BusinessMessages.RentMessages.RENT_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
        this.rentalRepository.delete(rental);
        return new SuccessResult(BusinessMessages.RentMessages.RENT_DELETED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalRepository.save(rental);
        return new SuccessResult(BusinessMessages.RentMessages.RENT_UPDATED_SUCCESSFULLY);
    }

    /*public Result updateRentalDeliveryDate(UpdateRentalDeliveryDateRequest updateRentalDeliveryDateRequest){

        checkIfRentalExistisOk(updateRentalDeliveryDateRequest.getId(),
                updateRentalDeliveryDateRequest.getDeliveryDate());
        return new SuccessResult("DeliveryDate added");
    }
*/

    @Transactional
    public Result UpdateEndKm(UpdateKmInfoRequest kmInfoRequest) {
        checkIfRentalNotExist(kmInfoRequest.getRentalId());
        Rental rental = this.rentalRepository.getById(kmInfoRequest.getRentalId());

        if (rental.getRentReturnDate().isEqual(LocalDate.now()) && !rental.isRentStatus()) {
            this.rentalRepository.updateEndKmInfoForRentalTable(kmInfoRequest.getRentalId(), kmInfoRequest.getEndKm());
            this.carService.updateCarKmInfo(kmInfoRequest);
            this.carService.updateStatus(kmInfoRequest.getCarId(), CarStates.available);
            return new SuccessResult(BusinessMessages.RentMessages.END_KILOMETER_INFO_UPDATED_SUCCESSFULLY);
        }
        return new ErrorResult(BusinessMessages.CarMessages.CAR_ALREADY_IN_RENT);

        //araba kiradan dönmüş mü? dönmüşse eğer aynı gün km bilgisi düzenlenip, durumlar car tablosunda da güncellenir
        //güncelleme araba kiradan döndüğü gün yapılmak zorundadır
    }

    public Result updateDeliveryDateForExtendingRental(CreateRentalDeliveryDateRequest createRentalDeliveryDateRequest) {
        if(checkIfRentalExist(createRentalDeliveryDateRequest.getRentalId())){
            Rental rental = this.rentalRepository.getById(createRentalDeliveryDateRequest.getRentalId());
            rental.setDeliveryDate(createRentalDeliveryDateRequest.getDeliveryDate());
            this.rentalRepository.save(rental);
            //return new SuccessResult("Kiralama Tarihi uzatıldı");
        }
        return new ErrorResult("Böyle bir kiralama sistemde mevcut değil");
    }

    @Override
    public Rental getById(int rentalId) {
        Rental rental = this.rentalRepository.getById(rentalId);
        return rental;
    }
    //kullanıcı kiralama süresini uzatmak isterse aynı rental üzerinden uzatıcak ve ek ödeme yapacak





    //-----------Business Rules--------------------------------------------------------------


    private Result checkIfCarAlreadyInRent(Rental rental) {

        checkIfCarisRented(rental.getCar().getId());
        checkIfRentReturnDate(rental.getRentReturnDate());
        checkIfCarisAlreadyisRentedWithState(rental.getCar().getId());
        return new SuccessResult(BusinessMessages.RentMessages.CAR_NOT_IN_RENT);
    }

    private void checkIfRentReturnDate(LocalDate rentReturnDate) {
        if (rentReturnDate == null) throw new BusinessException("tesim tarihi boş bırakılamaz");
        if (rentReturnDate.isBefore(LocalDate.now())) {
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
        for (Rental r : this.rentalRepository.getAllByCarId(carId)) {
            if (r.isRentStatus()) {
                r.getCar().setStatus(CarStates.rented);
                throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_RENT);
            }
        }
    }

    private void checkIfRentalNotExist(int rentalId) {
        if (!this.rentalRepository.existsById(rentalId))
            throw new BusinessException(BusinessMessages.RentMessages.RENT_NOT_FOUND);
    }

    //    private void checkIfRentalExistisOk(int rentalId, LocalDate deliveryDate){
//       if(checkIfRentalExist(rentalId)) {
//           Rental rental = this.rentalRepository.getById(rentalId);
//           rental.setDeliveryDate(deliveryDate);
//       }
//    }
//
    public boolean checkIfRentalExist(int rentalId) {
       return this.rentalRepository.existsById(rentalId);
    }


}


