package com.etiya.renACar.business.model.requests.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalProductRequest {

    @NotNull
    private int id;

    @NotNull
    private String productName;

    @NotNull
    private double unitPrice;

    private int discountAmount;
}
