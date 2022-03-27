package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMaintenanceRepository extends JpaRepository<CarMaintenance,Integer> {

    List<CarMaintenance> getByCarId(int carId);



}
