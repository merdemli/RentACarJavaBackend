package com.etiya.renACar.business.model.requests.createRequest;

import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import com.etiya.renACar.model.entities.concretes.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {


    @JsonIgnore
    private int id;

    private String creditCardNo;

    private String cardHolder;

    private LocalDate expirationDate;  // TODO: 4/11/2022 bu kısım creditCard bölümüne taşınacak

    private String cvv;

    private int userId;

//------------------------Rental-----------------

    private CreateRentalRequest createRentalRequest;

    //--------------------Invoice----------------------------

    private List<CreateOrderedAdditionalProductRequest>orderedAdditionalProductRequests;

//------------------OrderedAdditionalProduct-----------------------
    private CreateInvoiceRequest createInvoiceRequest;

   // private CreateRentalForDailyPriceRequest createRentalForDailyPriceRequest;
}

//User eklenmiyor çünkü sisteme kayt olunmadan araba kiralanamıyor bu sistemde
// TODO: 4/11/2022 sisteme kullanıcı kaydı ekle
