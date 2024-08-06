package com.example.springbasic.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    Payment sut;

    @Test
    void createPayment() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Payment payment = Payment.of(1L, "USD", BigDecimal.valueOf(1), BigDecimal.valueOf(1_000), LocalDateTime.now(clock));

        LocalDateTime now = LocalDateTime.now(clock);

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment.getValidUntil()).isEqualTo(now.plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Payment payment = Payment.of(1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_300), LocalDateTime.now(clock));

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(payment.isValid(Clock.offset(clock, Duration.of(29L, ChronoUnit.MINUTES)))).isTrue();
    }

}
