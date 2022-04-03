package com.etiya.renACar.model.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Car> cars;

    @OneToMany(mappedBy = "rentalCity") //Rentaldaki city alanını bulur ilişki kurar
    private List<Rental>rentalCity;

    @OneToMany(mappedBy = "deliveryCity")
    private List<Rental>deliveryCities;
}

