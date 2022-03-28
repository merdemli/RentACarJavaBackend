package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> getByModelYear(int modelYear);
    List<Car>getByModelYearIn(List<Integer>modelYears);
    List<Car>getByModelYearAndDailyPrice(int modelYear, double dailyPrice);
    List<Car>getByDescriptionContains(String description);
    List<Car>getAllByStatus(CarState type); //bakımda olan araçlar listelenebilmelidir

    Car getCarById(int CarId);


//
//    @Modifying
//    @Query("update Car set color.colorId=?2 where carId=?1")
//    int updateColorToCarByCarId(int carId, int colorId);

    Car getCarByIdAndStatus(int carId, CarState type);
}
