package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.QrPaymentRequestDTO;
import com.wallet.zokudo.services.payment.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/pay")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "Bharat qr scan payment", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/bharat")
    public Object bharatPay(@RequestBody final QrPaymentRequestDTO qrPaymentRequestDTO,
                            @PathVariable("programUrl") final String programUrl) {
        return paymentService.makeBharatPayment(qrPaymentRequestDTO,programUrl);
    }
}
