package com.example.springbasic;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        System.out.println(payment);
    }

}
