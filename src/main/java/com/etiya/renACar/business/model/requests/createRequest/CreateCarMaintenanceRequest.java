package com.etiya.renACar.business.model.requests.createRequest;


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
public class CreateCarMaintenanceRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate addedDate ;

    private LocalDate returnedDate;

    @NotNull
    private String description;

    @NotNull
    private int carId;
}
