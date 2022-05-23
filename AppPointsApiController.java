package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.PointBalanceDTO;
import com.wallet.zokudo.services.AppPointBalanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("appuser/api/v1")
public class AppPointsApiController {
    private final AppPointBalanceService appPointBalanceService;
    private final String APP_URL = "appuser";

    @Autowired
    public AppPointsApiController(final AppPointBalanceService appPointBalanceService) {
        this.appPointBalanceService = appPointBalanceService;
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Get customer total point balance by mobile number", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/pointBalance/mobile", produces = "application/json")
    public ResponseEntity<?> customerPointBalance(@RequestBody PointBalanceDTO pointBalanceDTO) {
        return appPointBalanceService.getCustomerPointBalance(pointBalanceDTO);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Get customer available points to be redeemed by program", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/redeemPoints/program", produces = "application/json")
    public ResponseEntity<?> customerPointRedemption(@RequestBody PointBalanceDTO pointBalanceDTO) {
        return appPointBalanceService.customerPointRedemption(pointBalanceDTO, APP_URL);
    }
}
