package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.MerchantAuthValidatorRequestDTO;
import com.wallet.zokudo.dto.request.MerchantCashbackRequestDTO;
import com.wallet.zokudo.services.MerchantAuthValidatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/AuthValidator")
public class MerchantAuthValidatorController {

    private final MerchantAuthValidatorService merchantAuthValidatorService;

    @Autowired
    public MerchantAuthValidatorController(final MerchantAuthValidatorService merchantAuthValidatorService) {
        this.merchantAuthValidatorService = merchantAuthValidatorService;
    }

    @ApiOperation(value = "Configure Merchant for Auth Validator", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/saveMerchant")
    public ResponseEntity<?> saveMerchant(@PathVariable("programUrl") final String programUrl,
                                                       @RequestBody @Valid final MerchantAuthValidatorRequestDTO merchantAuthValidatorRequestDTO) {
        return merchantAuthValidatorService.saveMerchantAuthValidator(merchantAuthValidatorRequestDTO);
    }

    @ApiOperation(value = "Edit Merchant for Auth Validator", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/editMerchant")
    public ResponseEntity<?> updateMerchant(@PathVariable("programUrl") final String programUrl,
                                          @RequestBody @Valid final MerchantAuthValidatorRequestDTO merchantAuthValidatorRequestDTO) {
        return merchantAuthValidatorService.editMerchant(merchantAuthValidatorRequestDTO);
    }

    @ApiOperation(value = "Fetch Merchant Auth Validator List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "fetchMerchantAuthValidatorList")
    public ResponseEntity<?> fetchMerchantAuthValidatorList(@PathVariable("programUrl") final String programUrl) {
        return merchantAuthValidatorService.fetchMerchantAuthValidatorList();
    }
}
