package com.etiya.renACar.business.model.responses.listResponseDto;

import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.etiya.renACar.model.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceListResponse {

    private int id;

    private double totalPrice;

    private String invoiceNo;

    private LocalDate rentDate;

    private LocalDate deliveryDate;

    private int totalRentDay;

    private int rentalId;

    private int userId;



}
