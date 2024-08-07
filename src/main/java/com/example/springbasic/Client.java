package com.example.springbasic;

import com.example.springbasic.payment.Payment;
import com.example.springbasic.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        System.out.println(payment);
    }

}
