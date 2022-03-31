package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.abstracts.CarService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateStatusForCarTableRequest;
import com.etiya.renACar.business.model.responses.getResponseDto.CarResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.CarMaintenanceListResponseDto;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.CarMaintenance;
import com.etiya.renACar.model.enums.CarStates;
import com.etiya.renACar.repository.abstracts.CarMaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private CarMaintenanceRepository carMaintenanceRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;



    public CarMaintenanceManager(CarMaintenanceRepository carMaintenanceRepository, ModelMapperService modelMapperService,
                                CarService carService ) {
        this.carMaintenanceRepository = carMaintenanceRepository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public void add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        checkIfExistsMaintenance(createCarMaintenanceRequest);

        CarMaintenance maintenance= this.modelMapperService.forRequest()
                .map(createCarMaintenanceRequest,CarMaintenance.class);
        this.carMaintenanceRepository.save(maintenance);

        UpdateStatusForCarTableRequest r = new UpdateStatusForCarTableRequest();
         r.setCarId(createCarMaintenanceRequest.getCarId());
        this.carService.updateMaintenanceStatus(r);

// To.Do save ekle

    }

    @Override
    public List<CarMaintenanceListResponseDto> getByCarId(int carId) {
        List<CarMaintenance>maintenances = this.carMaintenanceRepository.getByCarId(carId);
        return map(maintenances);

    }
    private List<CarMaintenanceListResponseDto> map(List<CarMaintenance> carMaintenances) {
        List<CarMaintenanceListResponseDto> dtos = carMaintenances.stream()//"stream of car" döner
                .map(m -> this.modelMapperService.forDto().map(m, CarMaintenanceListResponseDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

    //------------------business rules------------------------------------------------------------------------

    private void checkIfExistsMaintenance(CreateCarMaintenanceRequest createCarMaintenanceRequest){
        CarResponseDto car1  = this.carService.getCarById(createCarMaintenanceRequest.getCarId());
        if(car1.getStatus()== CarStates.Maintenance)
            throw new BusinessException(BusinessMessages.CarMessages.CAR_ALREADY_IN_MAINTENANCE);
    }
}
