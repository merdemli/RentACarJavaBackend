package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> getAllByCarId(int carId);
    boolean existsRentalByCar_Id(int carId);
}
