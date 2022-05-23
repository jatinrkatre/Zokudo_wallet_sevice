package com.wallet.zokudo.controllers;

import java.util.Map;

import com.wallet.zokudo.dto.request.CollectiveCommissionValuesDTO;
import com.wallet.zokudo.services.RetailerDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wallet.zokudo.dto.request.CommissionUpdateDTO;
import com.wallet.zokudo.services.CommissionRetailerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/commmission/retailer")
public class CommissionRetailerController {

    private final CommissionRetailerService commissionRetailerService;
    private final RetailerDiscountService retailerDiscountService;

    @Autowired
    public CommissionRetailerController(final CommissionRetailerService commissionRetailerService,
                                        final RetailerDiscountService retailerDiscountService) {
        this.commissionRetailerService = commissionRetailerService;
        this.retailerDiscountService = retailerDiscountService;
    }

    @ApiOperation(value = "set default commission for on-boarding retailer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping("/addDefaultRetailerComm")
    public ResponseEntity<?> addDefaultComm(@RequestHeader long distributorId, @RequestHeader long programId) {
        commissionRetailerService.addDefaultCommission(programId, distributorId);
        return new ResponseEntity<>("Default commission added for the program", HttpStatus.OK);
    }

    @ApiOperation(value = "fetch all commission by retailer Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getRetailerComm", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getCommissionListByDistributor(@RequestHeader("distributorId") String distributorId,
                                              @RequestHeader("page") String page,
                                              @RequestHeader("size") String size,
                                              @RequestHeader("order") String order) {
        return commissionRetailerService.fetchCommissionByRetailerId(Integer.parseInt(page), Integer.parseInt(size), order, distributorId);
    }

    @ApiOperation(value = "update commission for retailer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateRetailerCommission", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRevenueValuesByDistributor(@RequestBody CommissionUpdateDTO CommissionUpdateDTO,
                                                              @PathVariable("programUrl") final String programUrl,
                                                              @RequestHeader("distributorId") String distributorId,
                                                              @RequestHeader("programId") String programId,
                                                              @RequestHeader("role") String role) {
        return commissionRetailerService.updateCommissionForRetailer(CommissionUpdateDTO, programUrl, distributorId, programId, role);
    }

    /*
     * Retailer Discount Commission
     * */
    @ApiOperation(value = "set default discount for on-boarding agent", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping("/addDefaultRetailerDiscount")
    public ResponseEntity<?> addDefaultRetailerDiscount(@RequestHeader("agentId") final long agentId,
                                                        @RequestHeader("programId") final long programId) {
        retailerDiscountService.addDefaultDiscountCommission(agentId, programId);
        return new ResponseEntity<>("Default discount commission added for the program", HttpStatus.OK);
    }

    @ApiOperation(value = "fetch all discount commission by agent Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getRetailerDiscount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getDiscountCommissionForRetailer(@RequestHeader("agentId") final String agentId,
                                                @RequestHeader("page") final String page,
                                                @RequestHeader("size") final String size,
                                                @RequestHeader("order") final String order,
                                                @RequestHeader("programId") final String programId) {
        return retailerDiscountService.fetchDiscountCommissionByAgentId(Integer.parseInt(page), Integer.parseInt(size), order, agentId, programId);
    }

    @ApiOperation(value = "update discount commission for retailer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateRetailerDiscount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRevenueValuesByDistributor(@RequestBody CollectiveCommissionValuesDTO CommissionUpdateDTO,
                                                              @PathVariable("programUrl") final String programUrl,
                                                              @RequestHeader("agentId") final String agentId,
                                                              @RequestHeader("programId") final String programId) {
        return retailerDiscountService.updateDiscountCommissionForRetailer(CommissionUpdateDTO, programUrl, agentId, programId);
    }
}
