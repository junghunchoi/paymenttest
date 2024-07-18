package org.example.paymenttest.dto;

import lombok.*;
import org.example.paymenttest.entity.Payment;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @NonNull
    private String payType; // 결제 타입 - 카드/현금/포인트
    @NonNull
    private Long amount; // 가격 정보
    @NonNull
    private String orderName; // 주문명
    private String yourSuccessUrl; // 성공 시 리다이렉트 될 URL
    private String yourFailUrl; // 실패 시 리다이렉트 될 URL

    public Payment toEntity() {
        return Payment.builder()
                .payType(payType)
                .amount(amount)
                .orderName(orderName)
                .orderId(UUID.randomUUID().toString())
                .paySuccessYN(false)
                .build();
    }
}
