package com.etiya.renACar.business.model.responses.ResponseDto;

import com.etiya.renACar.model.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarDto {

    private int id;

    private double dailyPrice;

    private String description;

    private int modelYear;

    private String colorName;

    private String brandName;

    private CarState status;

}
