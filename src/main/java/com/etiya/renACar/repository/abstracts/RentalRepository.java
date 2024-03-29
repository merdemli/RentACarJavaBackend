package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> getAllByCarId(int carId);
    boolean existsByCar_Id(int carId);


    @Modifying
    @Query ("update Rental r set r.endKm = ?2  where r.id =?1 ")
    int updateEndKmInfoForRentalTable(int rentalId,double endingKm);
}
