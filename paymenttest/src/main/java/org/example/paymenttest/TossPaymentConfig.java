package org.example.paymenttest;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;  // 이 부분을 수정
import org.springframework.stereotype.Component;

@Component
@Getter
public class TossPaymentConfig {
    public static final String TOSS_API_SERVER = "https://api.tosspayments.com/v1/payments";

    @Value("${payment.toss.test_client_api_key}")
    private String testClientKey;

    @Value("${payment.toss.test_secret_api_key}")
    private String testSecretKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;

    @Value("${payment.toss.fail_url}")
    private String failUrl;
}