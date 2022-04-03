package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additional_products")
public class AdditionalProduct extends Base {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "product_name")
    private String productName;

    @Column(name= "unit_price")
    private double unitPrice;

    @Column(name = "discount_amount")
    private int discountAmount;

    @OneToMany(mappedBy = "additionalProduct") //OAP'daki AP'ı bul maple
    private List<OrderedAdditionalProduct> orderedAdditionalProducts;
}
