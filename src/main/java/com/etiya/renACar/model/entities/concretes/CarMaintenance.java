package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carMaintenance")
public class CarMaintenance extends Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "addedDate")
    private LocalDate addedDate;

    @Column(name = "returnedDate")
    private LocalDate returnedDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
}
