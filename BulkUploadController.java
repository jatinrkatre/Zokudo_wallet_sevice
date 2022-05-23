package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.BulkLoadChecks;
import com.wallet.zokudo.dto.request.WalletLoadDto;
import com.wallet.zokudo.services.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1/")
public class BulkUploadController {

    private final WalletService walletService;

    @Autowired
    public BulkUploadController(final WalletService walletService) {
        this.walletService = walletService;
    }

    @ApiOperation(value = "Load Bulk Card Checks", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "client/{clientHashId}/BulkLoadChecks")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> bulkLoad(HttpServletRequest request,
                                      @PathVariable(value = "clientHashId") final String clientHashId,
                                      @PathVariable("programUrl") final String programUrl,
                                      @RequestBody @Valid final BulkLoadChecks bulkLoadChecks) {
        return walletService.bulkWalletChecks(bulkLoadChecks, clientHashId, programUrl, request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "Load Bulk Card Checks", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "client/{clientHashId}/customer/{customerHashId}/CustomerKycChecks")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> customerKycChecks(HttpServletRequest request,
                                               @PathVariable(value = "customerHashId") final String customerHashId,
                                               @PathVariable("programUrl") final String programUrl,
                                               @PathVariable(value = "clientHashId") final String clientHashId,
                                               @RequestBody @Valid final BulkLoadChecks bulkLoadChecks) {
        return walletService.customerKycChecks(bulkLoadChecks, customerHashId, programUrl, clientHashId, request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "Load Bulk Card Checks", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "client/{clientHashId}/validateAndSaveBulkRecord")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> validateAndSaveBulkRecord(HttpServletRequest request,
                                                       @PathVariable(value = "clientHashId") final String clientHashId,
                                                       @PathVariable("programUrl") final String programUrl,
                                                       @RequestBody @Valid final BulkLoadChecks bulkLoadChecks) {
        return walletService.validateAndSaveBulkRecord(bulkLoadChecks, clientHashId, programUrl, request.getHeader("role"),
                request.getHeader("loggedInUserHashId"));
    }
}
