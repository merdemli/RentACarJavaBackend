package com.etiya.renACar.outSourceServices.qnbFinansbankPaymentService;

import java.time.LocalDate;

public interface QnbFinansBankPaymentService {

    boolean isPaymentSuccess(LocalDate expirationDate, String cvv, String creditCardNo, String cardHolder);

    }

