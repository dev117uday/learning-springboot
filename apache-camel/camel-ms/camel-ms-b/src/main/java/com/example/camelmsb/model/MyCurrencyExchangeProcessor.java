package com.example.camelmsb.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class MyCurrencyExchangeProcessor {

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        log.info("some processing on currency exchange");
        return currencyExchange;
    }

}
