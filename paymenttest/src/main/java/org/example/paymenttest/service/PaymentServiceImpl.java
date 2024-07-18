package org.example.paymenttest.service;

import org.example.paymenttest.entity.Member;
import org.example.paymenttest.entity.Payment;
import org.example.paymenttest.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment requestTossPayment(Payment payment) {
        if (payment.getAmount() < 1000) {
            throw new RuntimeException("Amount must be greater than or equal to 1000");
        }

        Member member = Member.builder()
                .name("test")
                .password("123")
                .build();

        payment.setCustomer(member);

        return paymentRepository.save(payment);
    }
}
