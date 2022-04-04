package com.etiya.renACar.business.model.requests.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKmInfoRequest {
    private int rentalId;

    private double endKm;

    private int carId;


}
