package com.example.springbasic.payment;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);

}
