package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.City;
import com.etiya.renACar.model.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private LocalDate rentDate;
    @NotNull
    private LocalDate deliveryDate;

    private boolean rentStatus =true ;//true kirada , false uygun

    @NotNull
    private int carId;

    @NotNull
    private int userId;

    @NotNull
    private int rentalCityId;

    @NotNull
    private int deliveryCityId;



}
