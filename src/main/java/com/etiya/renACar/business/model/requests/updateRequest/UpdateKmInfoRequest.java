package com.etiya.renACar.business.model.requests.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKmInfoRequest {

    @NotNull
    private int rentalId;

    @NotNull
    private double endKm;

    private int carId;


}
