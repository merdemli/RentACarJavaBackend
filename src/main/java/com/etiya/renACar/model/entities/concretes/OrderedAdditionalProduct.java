package com.etiya.renACar.model.entities.concretes;


import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "additional_product_id")
    private AdditionalProduct additionalProduct;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public AdditionalProduct getAdditionalProduct() {
        return additionalProduct;
    }

    public void setAdditionalProduct(AdditionalProduct additionalProduct) {
        this.additionalProduct = additionalProduct;
    }

    public double getTotalPrice() {
        return totalPrice = additionalProduct.getUnitPrice()*amount;
    }
}
