package com.etiya.renACar.model.entities.concretes;


import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_additional_products")
public class OrderedAdditionalProduct extends Base {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "amount")
    private int amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "additional_product_id")
    private AdditionalProduct additionalProduct;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
