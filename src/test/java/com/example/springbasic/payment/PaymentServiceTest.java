package com.example.springbasic.payment;

import com.example.springbasic.exrate.WebApiExRateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    PaymentService sut;

    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증")
    @Test
    void prepare() throws IOException {
        sut = new PaymentService(new WebApiExRateProvider());

        Payment payment = sut.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 가져오기
        // 가져온 값이 뭔지 어떻게 알 수 있나..?
        assertThat(payment.getExRate()).isNotNull();

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 유효시간 계산
        // prepare당시의 시간은 언제인지 알 수 없나...?
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

}