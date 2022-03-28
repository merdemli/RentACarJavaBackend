package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carDamages")
public class CarDamage extends Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="carDamageDate")
    private LocalDate carDamageDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name ="car_id")
    private Car car;
}
