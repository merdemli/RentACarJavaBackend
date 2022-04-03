package com.etiya.renACar.business.model.responses.listResponseDto;

import com.etiya.renACar.model.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListResponse {

    private int id;

    private double dailyPrice;

    private String description;

    private int modelYear;

    private String colorName;

    private String brandName;

    @Enumerated(EnumType.STRING)
    private CarStates status;

}
