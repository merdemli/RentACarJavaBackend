package com.etiya.renACar.business.model.responses.getResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageResponseDto {

    private int id;

    private String damageDate;

    private String description;

    private String carDescription;

    private int carId;

}
