package com.example.camelmsb.controller;

import com.example.camelmsb.model.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversion(@PathVariable("from") String from, @PathVariable("to") String to) {
        return new CurrencyExchange(10001L, from, to, BigDecimal.TEN);
    }

}
