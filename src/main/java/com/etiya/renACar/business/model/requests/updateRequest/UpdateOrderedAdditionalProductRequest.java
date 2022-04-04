package com.etiya.renACar.business.model.requests.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalProductRequest {

    @NotNull
    private int id;

    private int amount;

    private int rentalId;

    private int additionalProductId;

    private int paymentId;

}
