package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.abstracts.RentalService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarMaintenanceListResponse;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.ErrorResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.CarMaintenance;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.CarMaintenanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private CarMaintenanceRepository carMaintenanceRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private RentalService rentalService;


    public CarMaintenanceManager(CarMaintenanceRepository carMaintenanceRepository, ModelMapperService modelMapperService,
                                 CarService carService, RentalService rentalService) {
        this.carMaintenanceRepository = carMaintenanceRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.rentalService = rentalService;
    }

    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {

        this.rentalService.checkIfCarisRented(createCarMaintenanceRequest.getCarId()); //araba kirada mı?
        checkIfCarisAlreadyInMaintenanceWithState(createCarMaintenanceRequest.getCarId());
        checkIfReturnDate(createCarMaintenanceRequest.getReturnedDate());


            CarMaintenance maintenance = this.modelMapperService.forRequest()
                    .map(createCarMaintenanceRequest, CarMaintenance.class);
            this.carMaintenanceRepository.save(maintenance);

        this.carService.updateStatus(createCarMaintenanceRequest.getCarId(),CarStates.maintenance);
        return new SuccessResult(BusinessMessages.MaintenanceMessages.CAR_MAINTENANCE_ADDED_SUCCESSFULLY);
    }

    @Override
    public List<CarMaintenanceListResponse> getByCarId(int carId) {
        List<CarMaintenance> maintenances = this.carMaintenanceRepository.getByCarId(carId);
        return map(maintenances);
    }

    private List<CarMaintenanceListResponse> map(List<CarMaintenance> carMaintenances) {
        List<CarMaintenanceListResponse> dtos = carMaintenances.stream()//"stream of car" döner
                .map(m -> this.modelMapperService.forDto().map(m, CarMaintenanceListResponse.class))
                .collect(Collectors.toList());
        return dtos;
    }

    //------------------business rules------------------------------------------------------------------------

    public Result checkIfCarisAlreadyInMaintenances(CarMaintenance maintenance) {

        checkIfCarisAlreadyInMaintenanceWithState(maintenance.getCar().getId());
        checkIfReturnDate(maintenance.getReturnedDate());
        return new SuccessResult(BusinessMessages.MaintenanceMessages.CAR_NOT_IN_MAINTENANCE);
    }

    private void checkIfCarisAlreadyInMaintenanceWithState(int carId) {
        CarResponseDto car1 = this.carService.getCarById(carId);
        if (car1.getStatus() == CarStates.maintenance)                          //1 CarTable'dan
            throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_MAINTENANCE);
        if(car1.getStatus() == CarStates.rented) throw new BusinessException("Araba kirada");

    }

    private void checkIfReturnDate(LocalDate returnDate) {
        if (returnDate != null) {
            if (!(returnDate.isBefore(LocalDate.now()) || returnDate.isEqual(LocalDate.now()))) {                                                          //2.
                throw new BusinessException("İleri tarih girilemez");
            }//To.Do :Bakımda kaldıgı gün sayısını ekle
        }
    }

//public void checkIfCarAlreadyInMaintenance (int carId){    //rentManager için yazıldı
////        for(CarMaintenance c: this.carMaintenanceRepository.getByCarId(carId)){
////            if(c.getCar().getStatus()==CarStates.maintenance)
////                throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_MAINTENANCE);
////    }
}

