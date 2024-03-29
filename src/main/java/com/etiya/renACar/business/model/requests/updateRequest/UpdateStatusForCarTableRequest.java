package com.etiya.renACar.business.model.requests.updateRequest;

import com.etiya.renACar.model.enums.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusForCarTableRequest {

    @NotNull
    private int carId;

    @NotNull
    private CarStates type;


}
