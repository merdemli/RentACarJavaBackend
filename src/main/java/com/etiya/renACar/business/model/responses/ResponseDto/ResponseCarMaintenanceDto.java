package com.etiya.renACar.business.model.responses.ResponseDto;

import com.etiya.renACar.model.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarMaintenanceDto {


    private int id;

    private LocalDate addedDate;

    private LocalDate returnedDate;

    private String description;

    private int carId;
}
