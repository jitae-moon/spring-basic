package com.example.springbasic;

import com.example.springbasic.api.ApiTemplate;
import com.example.springbasic.api.ErApiExRateExtractor;
import com.example.springbasic.api.SimpleApiExecutor;
import com.example.springbasic.exrate.WebApiExRateProvider;
import com.example.springbasic.payment.ExRateProvider;
import com.example.springbasic.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

}
