package com.faketri.market.infastructure.user.payload.payment.controller;

import com.faketri.market.entity.user.payload.payment.model.Payment;
import com.faketri.market.entity.user.payload.payment.model.PaymentStatus;
import com.faketri.market.infastructure.user.payload.payment.dto.PaymentResponse;
import com.faketri.market.infastructure.user.payload.payment.gateway.PaymentService;
import com.faketri.market.usecase.user.payload.payment.mapper.PaymentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@CrossOrigin({"http://localhost:8081", "http://192.168.1.106:8081/"})
@RequestMapping(path = "/api/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/{id}/status/paid", method = RequestMethod.GET)
    public PaymentResponse updateStatus(@PathVariable("id") final UUID paymentId){
        log.info("updateStatus: id - " + paymentId);
        return PaymentsMapper.toDto(paymentService.updateStatus(paymentId, PaymentStatus.PAID));
    }
}
