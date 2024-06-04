package com.faketri.market.usecase.user.payload.payment.mapper;

import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;
import com.faketri.market.infastructure.user.payload.payment.dto.PaymentResponse;

public class PaymentsMapper {

    public static PaymentResponse toDto(Payment payment){
        return new PaymentResponse(payment.getId(), payment.getPaymentStatus(), payment.getDateOfCreate(), payment.getDateOfPayment());
    }

    public static PaymentResponse toObj(PaymentResponse payment){
        return new PaymentResponse(payment.getId(), PaymentStatus.valueOf(payment.getPaymentStatus()), payment.getDateOfCreate(), payment.getDateOfPayment());
    }
}
