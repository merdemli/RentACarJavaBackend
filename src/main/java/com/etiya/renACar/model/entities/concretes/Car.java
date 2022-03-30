package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import com.etiya.renACar.model.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(0)
    @Column(name = "daily_price")
    private double dailyPrice;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "model_year")
    private int modelYear;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "car")
    private List<CarDamage> damages;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private CarStates status;

    @OneToMany(mappedBy = "car") //car maintenance'daki car alanını bulur,id'ye göre  mapler
    private List<CarMaintenance>carMaintenances;
}
