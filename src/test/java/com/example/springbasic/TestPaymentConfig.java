package com.example.springbasic;

import com.example.springbasic.payment.ExRateProvider;
import com.example.springbasic.payment.ExRateProviderStub;
import com.example.springbasic.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(getExRate(), clock());
    }

    @Bean
    public ExRateProvider getExRate() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

}
