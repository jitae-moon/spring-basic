package com.example.springbasic.exrate;

import com.example.springbasic.api.ApiTemplate;
import com.example.springbasic.api.ErApiExRateExtractor;
import com.example.springbasic.api.HttpClientApiExecutor;
import com.example.springbasic.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {

    private ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
