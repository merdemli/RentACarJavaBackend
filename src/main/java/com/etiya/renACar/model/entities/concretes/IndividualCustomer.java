package com.etiya.renACar.model.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "individual_customers")

@PrimaryKeyJoinColumn(name = "user_id")
public class IndividualCustomer extends Customer{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "individual_customer_id")
//    private int individualCustomerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name = "national_identity")
    private String nationalIdentity;
}
