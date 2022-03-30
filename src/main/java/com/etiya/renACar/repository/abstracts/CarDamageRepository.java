package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDamageRepository extends JpaRepository<CarDamage, Integer> {

    List<CarDamage> getByCarId(int carId); //getAllByCarId

}
