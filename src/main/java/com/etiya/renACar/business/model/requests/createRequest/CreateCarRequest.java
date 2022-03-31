package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.enums.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    @JsonIgnore
    private int id;

    @NotNull
    @Min(1)
    @Max(2000)
    private double dailyPrice;

    @NotNull
    @Length(min=2, max=50)
    private String description;

    @NotNull
    @Min(2000)
    private int modelYear;

    @NotNull
    private int colorId;

    @NotNull
    private int brandId;

    @Enumerated(EnumType.STRING)
    private CarStates status;
}
