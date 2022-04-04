package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.AdditionalProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalProductRequest {
    @JsonIgnore
    private int id;

    private int amount;

    private int rentalId;

    private int additionalProductId;

    private int paymentId;



}
