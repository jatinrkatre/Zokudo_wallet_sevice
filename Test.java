package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.TransactionRequest;
import com.wallet.zokudo.dto.request.WalletLoadDto;
import com.wallet.zokudo.dto.response.TransactionResponse;
import com.wallet.zokudo.services.BaseAuthorizationService;
import com.wallet.zokudo.services.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class Test {

    private final BaseAuthorizationService baseAuthorizationService;
    private final WalletService walletService;

    @Autowired
    public Test(final BaseAuthorizationService baseAuthorizationService,
                final WalletService walletService) {
        this.baseAuthorizationService = baseAuthorizationService;
        this.walletService = walletService;
    }


    @ApiOperation(value = "Authorize a transaction", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/test/authDebit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> authorizeTransaction() {
        for (int i = 0; i < 5; i++) {
            log.info(" Loop Value :{}", i);
            authTestThread();
        }
        return new ResponseEntity<>("Tested", HttpStatus.OK);
    }

    private void authTestThread() {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    TransactionRequest transactionRequest = new TransactionRequest();
                    transactionRequest.setFee("0");
                    transactionRequest.setMerchantName("TEST Merchant");
                    transactionRequest.setTerminalID("1002");
                    transactionRequest.setMcc("780");
                    transactionRequest.setTraceNo("989001");
                    transactionRequest.setInstitutionCode("123456");
                    transactionRequest.setRetrievalRefNo("910558687200");
                    transactionRequest.setSystemTraceAuditNumber("989001");
                    transactionRequest.setEntityId("9d4e6dc7-1d60-4273-88b7-3359c01a1e5a");
                    transactionRequest.setProxyCardNo("900009966");
                    transactionRequest.setNetwork("RUPAY");
                    transactionRequest.setChannel("ECOM");
                    transactionRequest.setTransactionCurrency("356");
                    transactionRequest.setBillingCurrency("356");
                    transactionRequest.setMerchantId("10001");
                    transactionRequest.setTenant("BUSINESS");
                    transactionRequest.setAmount("1000");
                    transactionRequest.setTransactionAmount("1000");
                    transactionRequest.setBillingAmount("1000");
                    transactionRequest.setTxnRefNo(System.nanoTime() + "");

                    try {
//                        TransactionResponse transactionResponse = baseAuthorizationService.authorizeTransaction(transactionRequest);
//                        log.info("txnRefNo :{}", transactionResponse.getTxnRefNo());
//                        log.info("Desc :{}", transactionResponse.getDescription());
//                        log.info("ErrorCode :{}", transactionResponse.getErrorCode());
//                        log.info("Balance :{}", transactionResponse.getBalance());
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        log.error("Exception Occurred here");
                    }
                }
            });
            thread.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("Exception occurred");
        }
    }

    @ApiOperation(value = "Authorize reversal transaction", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/test/authReversal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> creditTransaction() {
//        String [] str = {"TESTAuth1000001030047","TESTAuth1000001030048","TESTAuth1000001030049","TESTAuth1000001030050"};
        String [] str = {"010008417204300832160000030008"};
        for (int i = 0; i < str.length; i++) {
            log.info(" Loop Value :{}", str[i]);
            authDebitAndCreditTestThread(str[i]);
        }
        return new ResponseEntity<>("Tested", HttpStatus.OK);
    }

    private void authDebitAndCreditTestThread(String txnRefNo) {
        try {

            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setFee("0");
            transactionRequest.setMerchantName("TEST Merchant");
//                    transactionRequest.setTerminalID("1002");
//                    transactionRequest.setMcc("780");
            transactionRequest.setTraceNo("989001");
            transactionRequest.setInstitutionCode("123456");
            transactionRequest.setRetrievalRefNo("rrn10000030035");
            transactionRequest.setSystemTraceAuditNumber("989001");
//                    transactionRequest.setEntityId("9d4e6dc7-1d60-4273-88b7-3359c01a1e5a");
            transactionRequest.setProxyCardNo("900009966");
            transactionRequest.setNetwork("RUPAY");
            transactionRequest.setChannel("ECOM");
            transactionRequest.setTransactionCurrency("356");
            transactionRequest.setBillingCurrency("356");
//                    transactionRequest.setMerchantId("10001");
            transactionRequest.setTenant("BUSINESS");
            transactionRequest.setAmount("1000");
            transactionRequest.setTransactionAmount("1000");
            transactionRequest.setBillingAmount("1000");
            transactionRequest.setTxnRefNo(txnRefNo);

            /*DEBIT*/
            TransactionRequest transactionRequest1 = new TransactionRequest();
            transactionRequest1.setFee("0");
            transactionRequest1.setMerchantName("TEST Merchant");
//                transactionRequest.setTerminalID("1002");
//                transactionRequest.setMcc("780");
            transactionRequest1.setTraceNo("989001");
            transactionRequest1.setInstitutionCode("123456");
            transactionRequest1.setRetrievalRefNo("910558687200");
            transactionRequest1.setSystemTraceAuditNumber("989001");
            transactionRequest1.setEntityId("9d4e6dc7-1d60-4273-88b7-3359c01a1e5a");
            transactionRequest1.setProxyCardNo("900009966");
            transactionRequest1.setNetwork("RUPAY");
            transactionRequest1.setChannel("ECOM");
            transactionRequest1.setTransactionCurrency("356");
            transactionRequest1.setBillingCurrency("356");
//                transactionRequest.setMerchantId("10001");
            transactionRequest1.setTenant("BUSINESS");
            transactionRequest1.setAmount("1000");
            transactionRequest1.setTransactionAmount("1000");
            transactionRequest1.setBillingAmount("1000");
            transactionRequest1.setTxnRefNo(txnRefNo);
//                transactionRequest.setTxnRefNo(System.nanoTime() + "");

            new Thread(() -> {
                try {
//                    TransactionResponse transactionResponse2 = baseAuthorizationService.authorizeTransaction(transactionRequest1);
//                    log.info("txnRefNo :{}", transactionResponse2.getTxnRefNo());
//                    log.info("Desc :{}", transactionResponse2.getDescription());
//                    log.info("ErrorCode :{}", transactionResponse2.getErrorCode());
//                    log.info("Balance :{}", transactionResponse2.getBalance());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    log.error("Exception Occurred here");
                }
            }).start();
            log.info("---------------------------------------------------------->");
            new Thread(() -> {
                try {
//                    TransactionResponse transactionResponse = baseAuthorizationService.authorizeReversalTransaction(transactionRequest);
//                    log.info("txnRefNo :{}", transactionResponse.getTxnRefNo());
//                    log.info("Desc :{}", transactionResponse.getDescription());
//                    log.info("ErrorCode :{}", transactionResponse.getErrorCode());
//                    log.info("Balance :{}", transactionResponse.getBalance());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    log.error("Exception Occurred here");
                }
            }).start();





        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("Exception occurred");
        }
    }

    @ApiOperation(value = "Wallet Load", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/test/walletLoad", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> walletLoad() {
        for (int i = 0; i < 2; i++) {
            log.info(" Loop Value :{}", i);
            walletLoadTest();
        }
        return new ResponseEntity<>("Tested", HttpStatus.OK);
    }

    private void walletLoadTest() {
        try {
            new Thread(() -> {
                try {
                    WalletLoadDto walletLoadDto = new WalletLoadDto();
                    walletLoadDto.setFundingChannel("prefund");
                    walletLoadDto.setAmount(10);
                    walletLoadDto.setCurrencyCode("INR");
                    walletLoadDto.setPocketName("DEFAULT");
                    walletLoadDto.setBulkTransactionType("SINGLE_LOAD");

                    walletService.walletLoad(walletLoadDto,
                            "mss",
                            "ROLE_AGENT",
                            "AGENTRWGJZQYBKP");
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    log.error("Exception Occurred here");
                }
            }).start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("Exception occurred");
        }
    }
}
