package com.etiya.renACar.business.model.responses.getResponseDto;

import com.etiya.renACar.model.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto {

    private int id;

    private double dailyPrice;

    private String description;

    private int modelYear;

    private String colorName;

    private String brandName;

    private CarStates status;
}
