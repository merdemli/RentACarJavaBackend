package com.etiya.renACar.business.model.requests.deleteRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarDamageRequest {
    @NotNull
    private int id;
}
