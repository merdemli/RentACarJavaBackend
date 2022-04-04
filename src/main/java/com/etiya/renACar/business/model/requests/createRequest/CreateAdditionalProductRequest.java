package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalProductRequest {

    @JsonIgnore
    private int id;

    @NotNull
    private String productName;

    @NotNull
    private double unitPrice;

    private int discountAmount;

}
