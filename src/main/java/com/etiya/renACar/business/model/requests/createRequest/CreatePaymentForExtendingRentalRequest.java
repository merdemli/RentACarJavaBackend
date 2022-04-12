package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentForExtendingRentalRequest {

    @JsonIgnore
    private int id;

    private String creditCardNo;

    private String cardHolder;    // TODO: 4/11/2022   : bu kısım creditCard bölümüne taşınacak

    private LocalDate expirationDate;

    private String cvv;

    private int userId;

    private CreateRentalDeliveryDateRequest createRentalDeliveryDateRequest; //deliveryDate güncellemek için

    //----------------------------------------------OrderedAdditionalProduct

    private CreateInvoiceRequest createInvoiceRequest;
}
