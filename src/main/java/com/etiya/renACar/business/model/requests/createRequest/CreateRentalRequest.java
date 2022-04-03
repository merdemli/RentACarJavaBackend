package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.City;
import com.etiya.renACar.model.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @JsonIgnore
    private int id;


    private LocalDate rentDate;


    private LocalDate deliveryDate;


    private boolean rentStatus =true ;//true kirada , false uygun


    private int carId;

    private int userId;

    private int rentalCityId;

    private int deliveryCityId;


}
