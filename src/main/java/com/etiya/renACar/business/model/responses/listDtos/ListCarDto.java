package com.etiya.renACar.business.model.responses.listDtos;

import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.model.entities.concretes.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int id;

    private double dailyPrice;

    private String description;

    private int modelYear;

    private String colorName;

    private String brandName;

}
