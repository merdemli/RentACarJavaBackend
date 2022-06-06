package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "credit_card_no")
    private String creditCardNo;

    @Column(name = "cardHolder")         // TODO: 4/12/2022 bunları creditCarda taşı 
    private String cardHolder;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "total_price")
    private double totalPrice;
    

//    @OneToMany(mappedBy = "payment")
//    private List<Invoice>invoices;  zaten rental da var
////
//    @OneToMany(mappedBy = "payment")
//    private List<OrderedAdditionalProduct>orderedAdditionalProducts;





}
