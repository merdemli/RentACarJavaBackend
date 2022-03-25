package com.etiya.renACar.business.model.requests.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    private double dailyPrice;

    private String description;

    private int modelYear;

    private int colorId;

    private int brandId;
}
