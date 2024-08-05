package com.example.springbasic.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    PaymentService sut;

    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증")
    @Test
    void convertedAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(1000), valueOf(10_000));
        testAmount(valueOf(1500), valueOf(15_000));
    }

    private void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        sut = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = sut.prepare(1L, "USD", TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }

}