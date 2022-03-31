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
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class CorporateCustomer extends Customer{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "corporate_customer_id")
//    private int corporateCustomerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name= "tax_number")
    private String taxNumber;
}
