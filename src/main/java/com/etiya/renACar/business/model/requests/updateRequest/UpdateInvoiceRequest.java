package com.etiya.renACar.business.model.requests.updateRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    @NotNull
    private int id;

    private String invoiceNumber;

    private double totalPrice;

    private LocalDate invoiceDate = LocalDate.now();

    private int rentalId;

    private int paymentId;
}
