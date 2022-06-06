package com.etiya.renACar.core.adapters.concretes;

import com.etiya.renACar.core.adapters.abstracts.BaseBankPaymentServiceAdapter;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.outSourceServices.halkBankPaymentService.HalkbankPaymentManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("halkbank")
public class HalkBankPaymentServiceAdapter implements BaseBankPaymentServiceAdapter {


    @Override
    public boolean checkIfPaymentSuccess(Payment payment) {

        HalkbankPaymentManager manager = new HalkbankPaymentManager();
        return manager.payment(payment.getCreditCardNo(), payment.getCardHolder(),
                payment.getExpirationDate(), payment.getCvv());

    }
}
