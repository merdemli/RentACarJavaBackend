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

    @Column(name = "first_name")
    private String cardHolder;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name ="is_success")
    private boolean isSuccess;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @OneToMany(mappedBy = "payment")
    private List<Invoice>invoices;

    @OneToMany(mappedBy = "payment")
    private List<OrderedAdditionalProduct>orderedAdditionalProducts;





}
