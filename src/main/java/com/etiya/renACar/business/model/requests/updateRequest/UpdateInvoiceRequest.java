package com.etiya.renACar.business.model.requests.updateRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateInvoiceRequest {
    @NotNull
    private int id;

    private String invoiceNumber;

    private double totalPrice;

    private LocalDate invoiceDate = LocalDate.now();

    private int rentalId;

    private int paymentId;
}
