package com.etiya.renACar.model.entities.concretes;

import com.etiya.renACar.model.entities.abstracts.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "rent_return_date")
    private LocalDate rentReturnDate; //ilk rental eklerken kullanılacak

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;  //teslim tarihini uzatırsa kullanılacak ve 2. ödeme alınacak

    @Column(name = "rent_status")
    private boolean rentStatus;//true kirada , false uygun

    @Column(name = "end_km")
    private double endKm;

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

    @OneToMany(mappedBy = "rental")
    private List<OrderedAdditionalProduct> orderedAdditionalProducts;

    @OneToMany(mappedBy = "rental")
    private List<Invoice>invoices;  //örnegin kiralama süresini uzattı,birden çok faturası olabilir

    @OneToMany(mappedBy = "rental")
    private List<Payment>payments; //birden çok ödemesi de olabilir

}
