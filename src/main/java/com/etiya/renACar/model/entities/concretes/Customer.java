package com.etiya.renACar.model.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User{

//    @Id
//    @Column(name = "customer_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int customerId;

    @Column(name = "date_registered")
    private LocalDate dateRegistered = LocalDate.now();

    @Column(name = "customer_number")
    private long customerNumber;


}
