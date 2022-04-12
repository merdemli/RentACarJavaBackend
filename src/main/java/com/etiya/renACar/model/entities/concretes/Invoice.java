package com.etiya.renACar.model.entities.concretes;


import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "total_rent_day")
    private int totalRentDay;

    /*@Column(name =" invoice_date")
    private LocalDate invoiceDate = LocalDate.now();*/   //base'den geliyor

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
