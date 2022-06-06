package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private LocalDate rentDate;
    @NotNull
    private LocalDate rentReturnDate; // ilk rental eklemede kullanılacak tarih

    private boolean rentStatus = true;//true kirada , false uygun

    @NotNull
    private int carId;

    @NotNull
    private int userId;

    @NotNull
    private int rentalCityId;

    @NotNull
    private int deliveryCityId;


}
