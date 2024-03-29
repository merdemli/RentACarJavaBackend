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
import com.etiya.renACar.core.utilities.helpers.HelperMetods;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.*;
import com.etiya.renACar.model.entities.concretes.Rental;
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
        this.orderedAdditionalProductService = orderedAdditionalProductService;
    }

    @Override
    public DataResult<Rental> add(CreateRentalRequest createRentalRequest) {

        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);


        checkIfCarAlreadyInRent(rental); //araba kirada mı

        this.carService.checkIfCarInMaintenance(createRentalRequest.getCarId()); //araba bakımda mı

        rental = this.rentalRepository.save(rental);

        this.carService.updateStatus(createRentalRequest.getCarId(), CarStates.rented); //car durumu güncellendi
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
        if (checkIfRentalExist(createRentalDeliveryDateRequest.getRentalId())) {
            Rental rental = this.rentalRepository.getById(createRentalDeliveryDateRequest.getRentalId());
            rental.setDeliveryDate(createRentalDeliveryDateRequest.getDeliveryDate());
            this.rentalRepository.save(rental);
        }
        return new ErrorResult(BusinessMessages.RentMessages.RENT_NOT_FOUND);
    }


    @Override
    public DataResult<Rental>getById(int rentalId) {
        Rental rental = this.rentalRepository.getById(rentalId);

        return new SuccessDataResult<Rental>(rental,BusinessMessages.RentMessages.RENT_FETCHED_SUCCESSFULLY);
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
        if (rentReturnDate == null) throw new BusinessException("");
        if (rentReturnDate.isBefore(LocalDate.now())) {
            throw new BusinessException(BusinessMessages.RentMessages.DELIVERY_DATE_IS_NOT_SUITABLE);
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

    public boolean checkIfRentalExist(int rentalId) {
        return this.rentalRepository.existsById(rentalId);
    }

    public Result checkIfDeliveryCityisDifferentRentalCity(int rentalId) {
        Rental rental = this.rentalRepository.getById(rentalId);

        if (!rental.getRentalCity().equals(rental.getDeliveryCity())) {
            return new SuccessResult(BusinessMessages.RentMessages.CITIES_ARE_DIFFERENT);
        }
        return new ErrorResult(BusinessMessages.RentMessages.CITIES_ARE_SAME);
    }

    public double calculateRentalPriceForCar(int rentalId) {
        Rental rental = this.rentalRepository.getById(rentalId);
        // araba söz verilen tarihte teslim edilmiş kiralama uzatılmamış
            int usageDays = HelperMetods.getDaysBetween(rental.getRentDate(), rental.getRentReturnDate());
            double rentalPrice= HelperMetods.calculateForMult(usageDays,rental.getDailyPrice());
            return rentalPrice;

    }

    public double calculateExtendingRentalPriceForCar(int rentalId){
        Rental rental = this.rentalRepository.getById(rentalId);
        if(!rental.getDeliveryDate().isEqual(rental.getRentReturnDate())){
            int usageDays = HelperMetods.getDaysBetween(rental.getRentReturnDate(),rental.getDeliveryDate());
            double rentalPriceForCar = HelperMetods.calculateForMult(usageDays,rental.getCar().getDailyPrice());
            return rentalPriceForCar;          //rentReturnDate //DeliveryDate arasında hesaplanır
        }
        throw new BusinessException(BusinessMessages.RentMessages.RENT_TOTAL_PRICE_CALCULATED_FAILED);
        }

//    private double calculateDailyPrice (int rentalId){
//        double dailyPrice = HelperMetods.caltulateForSum(this.orderedAdditionalProductService.calculateOrderedAdditionalPrice(rentalId).getData(),
//                this.getById(rentalId).getCar().getDailyPrice());
//        return dailyPrice;
//    }


}






