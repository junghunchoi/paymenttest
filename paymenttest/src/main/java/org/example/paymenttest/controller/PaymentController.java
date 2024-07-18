package org.example.paymenttest.controller;

import lombok.RequiredArgsConstructor;
import org.example.paymenttest.TossPaymentConfig;
import org.example.paymenttest.dto.PaymentDTO;
import org.example.paymenttest.dto.PaymentResDto;
import org.example.paymenttest.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments/toss")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    private TossPaymentConfig tossPaymentConfig;

//    @PostMapping("/toss")
//    public ResponseEntity<Object> requestTossPayment(PaymentDTO paymentDTO) {
//        PaymentResDto paymentResDto = paymentService.requestTossPayment(paymentDTO.toEntity()).toPaymentResDto();
//
//        paymentResDto.setSuccessUrl(paymentDTO.getYourSuccessUrl() == null ? tossPaymentConfig.getSuccessUrl() : paymentDTO.getYourSuccessUrl());
//        paymentResDto.setFailUrl(paymentDTO.getYourFailUrl() == null ? tossPaymentConfig.getFailUrl() : paymentDTO.getYourFailUrl());
//
//        return ResponseEntity.ok(paymentResDto);
//    }

    @PostMapping("/success")
    public ResponseEntity<Object> success() {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/fail")
    public ResponseEntity<Object> fail() {
        return ResponseEntity.ok("fail");
    }
}
