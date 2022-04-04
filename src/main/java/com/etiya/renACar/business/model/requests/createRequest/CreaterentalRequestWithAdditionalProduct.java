package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.City;
import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreaterentalRequestWithAdditionalProduct {

    @JsonIgnore
    private int id;

    private LocalDate rentDate;

    private LocalDate deliveryDate;

    private boolean rentStatus =true ;//true kirada , false uygun

    private int carId;

    private int userId;

    private int rentalCityId;

    private int deliveryCityId;

    @JsonIgnore
    private int orderedAdditionalId;

    @NotNull
    private String productName;

    @NotNull
    private double unitPrice;

    private int discountAmount;


}
