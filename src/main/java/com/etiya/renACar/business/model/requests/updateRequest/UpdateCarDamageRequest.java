package com.etiya.renACar.business.model.requests.updateRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDamageRequest {



    @NotNull
    private int id; //Update requestlerde id zorunludur

    @NotNull
    private LocalDate carDamageDate;

    private String description;

    @NotNull
    private int carId;
}
