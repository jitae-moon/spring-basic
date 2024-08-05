package com.example.springbasic;

import com.example.springbasic.payment.ExRateProvider;
import com.example.springbasic.payment.ExRateProviderStub;
import com.example.springbasic.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(getExRate());
    }

    @Bean
    public ExRateProvider getExRate() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}
