package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.CollectiveCommissionValuesDTO;
import com.wallet.zokudo.dto.request.CommissionUpdateDTO;
import com.wallet.zokudo.services.ClientCommissionService;
import com.wallet.zokudo.services.ClientDiscountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/commission/client")
public class ClientCommissionController {

    private final ClientCommissionService clientCommissionService;
    private final ClientDiscountService clientDiscountService;

    @Autowired
    public ClientCommissionController(final ClientCommissionService clientCommissionService,
                                      final ClientDiscountService clientDiscountService) {
        this.clientCommissionService = clientCommissionService;
        this.clientDiscountService = clientDiscountService;
    }

    @ApiOperation(value = "set default commission for on-boarding client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping("/addDefaultClientCommission")
    public ResponseEntity<?> addDefaultClientCommission(@RequestHeader("clientId") final long clientId,
                                                        @RequestHeader("programId") final long programId) {
        clientCommissionService.addDefaultCommission(programId, clientId);
        return new ResponseEntity<>("Default commission added for the program", HttpStatus.OK);
    }

    @ApiOperation(value = "fetch all commission by client Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getClientCommission", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getCommissionListByDistributor(@RequestHeader("clientId") final String clientId,
                                              @RequestHeader("page") final String page,
                                              @RequestHeader("size") final String size,
                                              @RequestHeader("order") final String order,
                                              @RequestHeader("programId") final String programId) {
        return clientCommissionService.fetchCommissionByClientAndProgram(Integer.parseInt(page), Integer.parseInt(size), order, clientId,programId);
    }

    @ApiOperation(value = "update commission for client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateClientCommission", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRevenueValuesByDistributor(@RequestBody CommissionUpdateDTO CommissionUpdateDTO,
                                                              @PathVariable("programUrl") final String programUrl,
                                                              @RequestHeader("clientId") final String clientId,
                                                              @RequestHeader("programId") final String programId) {
        return clientCommissionService.updateCommissionForClient(CommissionUpdateDTO, programUrl, clientId, programId);
    }

    /*
     * Add Discount for corporates.
     * */
    @ApiOperation(value = "set default discount for on-boarding client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping("/addDefaultClientDiscount")
    public ResponseEntity<?> addDefaultClientDiscount(@RequestHeader("clientId") final long clientId,
                                                      @RequestHeader("programId") final long programId) {
        clientDiscountService.addDefaultDiscountCommission(clientId, programId);
        return new ResponseEntity<>("Default discount commission added for the program", HttpStatus.OK);
    }

    @ApiOperation(value = "fetch all discount commission by client Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/getClientDiscount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getDiscountCommissionForClient(@RequestHeader("clientId") final String clientId,
                                              @RequestHeader("page") final String page,
                                              @RequestHeader("size") final String size,
                                              @RequestHeader("order") final String order,
                                              @RequestHeader("programId") final String programId) {
        return clientDiscountService.fetchCommissionByClientId(Integer.parseInt(page), Integer.parseInt(size), order, clientId,programId);
    }

    @ApiOperation(value = "update commission for client", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateClientDiscount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRevenueValuesByDistributor(@RequestBody CollectiveCommissionValuesDTO CommissionUpdateDTO,
                                                              @PathVariable("programUrl") final String programUrl,
                                                              @RequestHeader("clientId") final String clientId,
                                                              @RequestHeader("programId") final String programId) {
        return clientDiscountService.updateDiscountCommissionForClient(CommissionUpdateDTO, programUrl, clientId, programId);
    }
}
