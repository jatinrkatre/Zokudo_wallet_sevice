package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.PointBalanceDTO;
import com.wallet.zokudo.dto.request.PointsLedgerDTO;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.entities.CashBackPointsLedger;
import com.wallet.zokudo.services.PointsLedgerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/pointRepository")
public class PointsLedgerController {

    private final PointsLedgerService pointsLedgerService;

    @Autowired
    public PointsLedgerController(PointsLedgerService pointsLedgerService){
        this.pointsLedgerService = pointsLedgerService;
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Ledger for points accumulation", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/accumulate", produces = "application/json")
    public ApiResponse accumulatePointLedger(@RequestBody PointsLedgerDTO dto) {
        return pointsLedgerService.accumulatePointsLedger(dto);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Ledger for points redeem", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/redeem", produces = "application/json")
    public ResponseEntity<?> redeemPointLedger(@RequestBody PointsLedgerDTO dto) {
        return pointsLedgerService.redeemPointsLedger(dto);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Ledger for point refund", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/refund", produces = "application/json")
    public ResponseEntity<?> refundPointLedger(@RequestBody PointsLedgerDTO dto) {
        return pointsLedgerService.refundPointsLedger(dto);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "fetch customer point balance ledger", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/list", produces = "application/json")
    public Page<CashBackPointsLedger> getPointBalanceLedger(@RequestBody PointsLedgerDTO dto) {
        return pointsLedgerService.cashBackPointList(dto);
    }

    @ApiOperation(value = "download point balance ledger list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value ="/downloadPointBalanceLedgerList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadBalanceledgerList(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam final Map<String, String> requestParams) throws Exception{
        try{
            pointsLedgerService.downloadBalanceledgerList(response,requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


}
