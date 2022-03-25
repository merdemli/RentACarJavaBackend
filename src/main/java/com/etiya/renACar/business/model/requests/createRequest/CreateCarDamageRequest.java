package com.etiya.renACar.business.model.requests.createRequest;


import com.etiya.renACar.model.entities.concretes.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {

    @JsonIgnore
    private int id;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate carDamageDate;

    private String description;

    private int carId;
}
