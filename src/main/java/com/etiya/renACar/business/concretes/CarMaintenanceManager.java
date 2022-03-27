package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.CarMaintenanceService;
import com.etiya.renACar.business.model.requests.createRequest.CreateCarMaintenanceRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseCarMaintenanceDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.CarMaintenance;
import com.etiya.renACar.repository.abstracts.CarMaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private CarMaintenanceRepository carMaintenanceRepository;
    private ModelMapperService modelMapperService;

    public CarMaintenanceManager(CarMaintenanceRepository carMaintenanceRepository, ModelMapperService modelMapperService) {
        this.carMaintenanceRepository = carMaintenanceRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        CarMaintenance maintenance= this.modelMapperService.forRequest().
                map(createCarMaintenanceRequest,CarMaintenance.class);
       this.carMaintenanceRepository.save(maintenance);
    }

    @Override
    public List<ResponseCarMaintenanceDto> getByCarId(int carId) {
        List<CarMaintenance>maintenances = this.carMaintenanceRepository.getByCarId(carId);
        return map(maintenances);

    }



    private List<ResponseCarMaintenanceDto> map(List<CarMaintenance> carMaintenances) {
        List<ResponseCarMaintenanceDto> dtos = carMaintenances.stream()//"stream of car" döner
                .map(m -> this.modelMapperService.forDto().map(m, ResponseCarMaintenanceDto.class))
                .collect(Collectors.toList());
        return dtos;
    }

    //------------------business rules------------------------------------------------------------------------


}
