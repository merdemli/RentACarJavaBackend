package com.etiya.renACar.business.model.responses.listResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListResponse {


    private int id;

    private LocalDate addedDate;

    private LocalDate returnedDate;

    private String description;

    private int carId;
}
