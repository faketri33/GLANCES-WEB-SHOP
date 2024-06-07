package com.faketri.market.infastructure.user.payload.payment.gateway;

import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;

import java.util.UUID;

;

public interface PaymentService {

    Payment findById(final UUID paymentId);

    Payment updateStatus(final UUID paymentId, final PaymentStatus paymentStatus);

    Payment save(Payment payment);
}
