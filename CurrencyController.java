package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.AddCurrencyDTO;
import com.wallet.zokudo.services.currency.CurrencyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/wallet/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(final CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @ApiOperation(hidden = true, value = "Add currency", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCurrency(@RequestBody @Valid AddCurrencyDTO currency, @PathVariable("programUrl") String programUrl) {
        return new ResponseEntity<>(currencyService.add(currency), HttpStatus.OK);
    }

}
