package com.etiya.renACar.business.model.requests.updateRequest;

import com.etiya.renACar.model.entities.concretes.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class UpdateRentalRequest {

    private int id;

    private LocalDate rentDate;

    private LocalDate deliveryDate;

    private boolean rentStatus;//true kirada , false uygun

    private int carId;

    private  int rentalCityId;

    private int deliveryCityId;


}
