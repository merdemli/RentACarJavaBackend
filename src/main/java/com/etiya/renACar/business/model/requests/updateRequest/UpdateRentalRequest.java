package com.etiya.renACar.business.model.requests.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {

    @NotNull
    private int id;

    private LocalDate rentDate;

    private LocalDate rentReturnDate;

    private boolean rentStatus;//true kirada , false uygun

    private double endKm;

    private int carId;

    private  int rentalCityId;

    private int deliveryCityId;


}
