package com.etiya.renACar.business.model.responses.listResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDamageListResponse {

    private int id;
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String damageDate;

    private String description;

    private String carDescription;

    private int carId;
}
