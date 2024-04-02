package com.faketri.market.usecase.user.payload.payment;

import com.faketri.market.entity.user.payload.payment.gateway.PaymentRepository;
import com.faketri.market.infastructure.user.payload.payment.gateway.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
