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
@Table(name = "rentals")
public class Rental extends Base { //kiralanma bilgisi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "rent_status")
    private boolean rentStatus;//true kirada , false uygun

    @ManyToOne
    @JoinColumn(name ="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "rental_city_id",referencedColumnName = "id")
    private City rentalCity;

    @ManyToOne
    @JoinColumn(name= "delivery_city_id",referencedColumnName = "id")
    private City deliveryCity;


}
