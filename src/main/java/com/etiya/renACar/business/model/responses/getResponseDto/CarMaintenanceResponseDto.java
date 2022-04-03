package com.etiya.renACar.business.model.responses.getResponseDto;

import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.enums.CarStates;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

public class CarMaintenanceResponseDto {

    private int id;

    private LocalDate addedDate;

    private LocalDate returnedDate;

    private String description;

    private int carId;

    private CarStates type;
}
