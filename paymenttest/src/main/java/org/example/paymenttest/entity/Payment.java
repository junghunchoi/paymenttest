package org.example.paymenttest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.paymenttest.dto.PaymentResDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    private Long paymentId;

    @Column(nullable = false, name = "pay_type")
    private String payType;

    @Column(nullable = false, name = "pay_amount")
    private Long amount;

    @Column(nullable = false, name = "pay_name")
    private String orderName;

    @Column(nullable = false, name = "order_id")
    private String orderId;

    @Column(nullable = false)
    private boolean paySuccessYN;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로딩 추가
    @JoinColumn(name = "customer_id") // 외래 키 컬럼 지정
    private Member customer;

    @Column
    private String paymentKey;

    @Column
    private String failReason;

    @Column(nullable = false)
    private boolean cancelYN;

    @Column
    private String cancelReason;

    // 생성 시간과 수정 시간 추가
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public PaymentResDto toPaymentResDto() {
        return PaymentResDto.builder()
                .payType(payType)
                .amount(amount)
                .orderName(orderName)
                .orderId(orderId)
                .customerEmail("azzudi@naver.com")
                .customerName(customer.getName())
                .createdAt(createdAt.toString())
                .cancelYN(cancelYN)
                .failReason(failReason)
                .build();
    }

}