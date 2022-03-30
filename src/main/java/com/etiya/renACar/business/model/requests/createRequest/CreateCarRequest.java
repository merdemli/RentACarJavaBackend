package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.enums.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @JsonIgnore
    private int id;

    private double dailyPrice;

    private String description;

    private int modelYear;

    private int colorId;

    private int brandId;

    private CarStates status;
}
