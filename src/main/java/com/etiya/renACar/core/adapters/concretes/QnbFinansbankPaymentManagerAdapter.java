package com.etiya.renACar.core.adapters.concretes;

import com.etiya.renACar.core.adapters.abstracts.BaseBankPaymentServiceAdapter;
import com.etiya.renACar.model.entities.concretes.Payment;
import com.etiya.renACar.outSourceServices.qnbFinansbankPaymentService.QnbFinansBankPaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("qnbfinansbank")
public class QnbFinansbankPaymentManagerAdapter implements BaseBankPaymentServiceAdapter {

    private QnbFinansBankPaymentService qnbFinansBankPaymentService;

    public QnbFinansbankPaymentManagerAdapter(QnbFinansBankPaymentService qnbFinansBankPaymentService) {
        this.qnbFinansBankPaymentService = qnbFinansBankPaymentService;
    }

    @Override
    public boolean checkIfPaymentSuccess(Payment payment) {
        System.out.println("qnbfinansbank");
        return this.qnbFinansBankPaymentService.isPaymentSuccess(payment.getExpirationDate(),
                payment.getCvv(),payment.getCreditCardNo(),payment.getCardHolder());
    }
}
