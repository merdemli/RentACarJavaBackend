package com.etiya.renACar.business.model.requests.updateRequest;

import com.etiya.renACar.model.enums.CarState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusForCarTableRequest {

    private int carId;

    @JsonIgnore
    private CarState type;


}
