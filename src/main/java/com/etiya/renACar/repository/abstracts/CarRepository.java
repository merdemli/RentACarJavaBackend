package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> getByModelYear(int modelYear);
    List<Car>getByModelYearIn(List<Integer>modelYears);
    List<Car>getByModelYearAndDailyPrice(int modelYear, double dailyPrice);
    List<Car>getByDescriptionContains(String description);
    List<Car>getAllByStatus(CarStateStatus type); //bakımda olan araçlar listelenebilmelidir


}
