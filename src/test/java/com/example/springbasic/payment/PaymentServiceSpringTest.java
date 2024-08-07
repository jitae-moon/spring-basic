package com.example.springbasic.payment;

import com.example.springbasic.TestPaymentConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired PaymentService sut;
    @Autowired ExRateProviderStub exRateProviderStub;
    @Autowired Clock clock;

    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증")
    @Test
    void convertedAmount() {
        Payment payment = sut.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        exRateProviderStub.setExRate(valueOf(1_500));
        payment = sut.prepare(2L, "USD", TEN);
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_500));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(15_000));
    }

    @Test
    void validUntil() {
        Payment payment = sut.prepare(1000L, "USD", valueOf(20));

        LocalDateTime expected = LocalDateTime.now(clock).plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expected);
    }

}