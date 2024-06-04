package com.faketri.market.usecase.user.payload.payment;

import com.faketri.market.entity.exception.ResourceNotFoundException;
import com.faketri.market.entity.user.payload.payment.gateway.PaymentRepository;
import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;
import com.faketri.market.infastructure.user.payload.payment.gateway.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findById(final UUID paymentId) {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    @Override
    public Payment updateStatus(UUID paymentId, PaymentStatus paymentStatus) {
        final Payment payment = findById(paymentId);

        if (payment == null) throw new ResourceNotFoundException("Платеж не найден.");

        payment.setPaymentStatus(paymentStatus);

        return save(payment);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
