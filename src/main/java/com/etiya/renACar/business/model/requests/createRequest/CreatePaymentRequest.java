package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {


    @JsonIgnore
    private int id;

    private String creditCardNo;

    private String cardHolder;

    private LocalDate expirationDate;

    private String cvv;

//------------------------Rental-----------------

    private CreateRentalRequest createRentalRequest;

    //-----------------------------------------------Invoice

    private List<CreateOrderedAdditionalProductRequest>orderedAdditionalProductRequests;

//----------------------------------------------OrderedAdditionalProduct
    private CreateInvoiceRequest createInvoiceRequest;
}
