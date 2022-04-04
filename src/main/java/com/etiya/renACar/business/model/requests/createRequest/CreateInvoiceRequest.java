package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.abstracts.Base;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest  {

    @JsonIgnore
    private int id;

    private String invoiceNumber;

    private double totalPrice;

    private LocalDate invoiceDate = LocalDate.now();

    private int rentalId;

    private int userId;

    private int paymentId;


}
