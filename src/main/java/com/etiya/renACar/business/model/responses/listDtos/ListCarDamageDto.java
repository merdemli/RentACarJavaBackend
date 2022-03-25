package com.etiya.renACar.business.model.responses.listDtos;

import com.etiya.renACar.model.entities.concretes.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCarDamageDto {

    private int id;

    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String damageDate;

    private String description;

    private String carDescription;

    private int carId;




}
