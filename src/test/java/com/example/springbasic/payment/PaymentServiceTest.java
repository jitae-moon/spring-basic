package com.example.springbasic.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    PaymentService sut;
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증")
    @Test
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000), clock);
        testAmount(valueOf(1000), valueOf(10_000), clock);
        testAmount(valueOf(1500), valueOf(15_000), clock);
    }

    @Test
    void validUntil() throws IOException {
        sut = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = sut.prepare(100L, "USD", TEN);

        LocalDateTime expectedValidUntil = LocalDateTime.now(clock).plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        sut = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = sut.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}