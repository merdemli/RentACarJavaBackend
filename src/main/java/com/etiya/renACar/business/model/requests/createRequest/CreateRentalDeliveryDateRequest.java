package com.etiya.renACar.business.model.requests.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalDeliveryDateRequest {

    private int rentalId;

    private LocalDate deliveryDate;
}

//müşteri kiralama süresini uzattıgında sisteme girip, aynı rental id ile yeni bir ödeme yapabilecek

