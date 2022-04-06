package com.etiya.renACar.outSourceServices.qnbFinansbankPaymentService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class QnbFinansbankPaymentManager implements QnbFinansBankPaymentService{
    @Override
    public boolean isPaymentSuccess(LocalDate expirationDate, String cvv, String creditCardNo,String cardHolder){
        return true;
    }
}
