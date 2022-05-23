package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.TransactionCreditRequest;
import com.wallet.zokudo.dto.request.TransactionRequest;
import com.wallet.zokudo.dto.response.AuthPartialReverseResponse;
import com.wallet.zokudo.dto.response.TransactionResponse;
import com.wallet.zokudo.services.BaseAuthorizationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class AuthorizationController {

    private final BaseAuthorizationService baseAuthorizationService;

    @Autowired
    public AuthorizationController(final BaseAuthorizationService baseAuthorizationService){
        this.baseAuthorizationService = baseAuthorizationService;
    }

    @ApiOperation(value = "Authorize a transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<JSONObject> authorizeTransaction(@Valid @RequestBody final String transactionRequest) {
        return new ResponseEntity(baseAuthorizationService.authorizeTransaction(transactionRequest), HttpStatus.OK);
    }

    /*@ApiOperation(value = "Authorize a transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TransactionResponse> authorizeTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizeTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }*/

    @ApiOperation(value = "Authorize a reversal transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/reversal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<JSONObject> authorizeReversalTransaction(@Valid @RequestBody final String transactionRequest) {
        return new ResponseEntity(baseAuthorizationService.authorizeReversalTransaction(transactionRequest), HttpStatus.OK);
    }

    /*@ApiOperation(value = "Authorize a reversal transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/reversal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TransactionResponse> authorizeReversalTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizeReversalTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }*/

   /*@ApiOperation(value = "Authorize a transaction", authorizations = {@Authorization(value = "basicAuth")})
   @PostMapping(value = "/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ResponseEntity<JSONObject> authorizeTransaction(@Valid @RequestBody final String transactionRequest) throws Exception {
       return new ResponseEntity<>(baseAuthorizationService.encryptedAuthorizeTransaction(transactionRequest), HttpStatus.OK);
   }

   @ApiOperation(value = "Authorize a reversal transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/reversal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<JSONObject> authorizeReversalTransaction(@Valid @RequestBody final String transactionRequest) throws Exception {
        return new ResponseEntity<>(baseAuthorizationService.encryptedAuthorizeReversalTransaction(transactionRequest), HttpStatus.OK);
    }*/
    @ApiOperation(value = "Authorize a credit transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/credit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<JSONObject> authorizeCreditTransaction(@Valid @RequestBody final String transactionRequest) {
        return new ResponseEntity(baseAuthorizationService.authorizePartialRefundTransaction(transactionRequest), HttpStatus.OK);
    }

   /* @ApiOperation(value = "Authorize a credit transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/credit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AuthPartialReverseResponse> authorizeCreditTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizePartialRefundTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }*/

    @ApiOperation(value = "Authorize a transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/plaintext/authorize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TransactionResponse> authorizeTransactionWithoutEncryption(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizeTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Authorize a reversal transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/plaintext/reversal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TransactionResponse> authorizeReversalTransactionWithoutEncryption(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizeReversalTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Authorize a credit transaction", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/plaintext/credit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AuthPartialReverseResponse> authorizeCreditTransactionWithoutEncryption(@Valid @RequestBody final TransactionRequest transactionRequest) {
        return new ResponseEntity<>(baseAuthorizationService.authorizePartialRefundTransactionWithoutEncryption(transactionRequest), HttpStatus.OK);
    }
}
