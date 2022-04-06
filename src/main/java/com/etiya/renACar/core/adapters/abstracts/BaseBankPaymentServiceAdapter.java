package com.etiya.renACar.core.adapters.abstracts;

import com.etiya.renACar.model.entities.concretes.Payment;

public interface BaseBankPaymentServiceAdapter {

    boolean checkIfPaymentSuccess(Payment payment);
}
