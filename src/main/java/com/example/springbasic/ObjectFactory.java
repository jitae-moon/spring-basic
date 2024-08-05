package com.example.springbasic;

import com.example.springbasic.exrate.CachedExRateProvider;
import com.example.springbasic.payment.ExRateProvider;
import com.example.springbasic.exrate.WebApiExRateProvider;
import com.example.springbasic.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

}
