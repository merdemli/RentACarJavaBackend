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
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Payment>payments;

    @OneToMany(mappedBy = "user") //rental'daki user alanını bulur
    private List<Rental> rentals;

    @OneToMany(mappedBy = "user")
    private List<Invoice>invoices;
}
