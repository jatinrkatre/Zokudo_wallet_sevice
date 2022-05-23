package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.RevenueUpdateDTO;
import com.wallet.zokudo.dto.request.TransactionBasedOnFiltersDTO;
import com.wallet.zokudo.services.RevenueManagementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/revenueMngnt")
public class RevenueManagementController {

    private final RevenueManagementService revenueManagementService;

    public RevenueManagementController(final RevenueManagementService revenueManagementService) {
        this.revenueManagementService = revenueManagementService;
    }

    @ApiOperation(value = "set default revenue details for the program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/addDefaultRevenue")
    public ResponseEntity<?> addDefaultRevenueForProgram(@RequestHeader("clientId") Long clientId, @RequestHeader("programId") Long programId, @PathVariable("programUrl") String programUrl) {
        revenueManagementService.addDefaultRevenueForProgram(clientId, programId, programUrl);
        return new ResponseEntity<>("Default revenue setup done for the program", HttpStatus.OK);
    }

    @ApiOperation(value = "get all revenues by programId", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/getRevenue", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public Map getRevenueListByProgram(@RequestHeader("programId") String programId,
                                    @RequestHeader("page") String page,
                                    @RequestHeader("size") String size,
                                    @RequestHeader("order") String order) {
        return revenueManagementService.fetchRevenueByProgramId(Integer.parseInt(page), Integer.parseInt(size), order, programId);
    }

    @ApiOperation(value = "update revenue for program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updateRevenue", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRevenueValuesByProgram(@RequestBody RevenueUpdateDTO revenueUpdateDTO,
                                                       @PathVariable("programUrl") final String programUrl,
                                                       @RequestHeader("clientId") String clientId,
                                                       @RequestHeader("programId") String programId) {
        revenueManagementService.updateRevenueForProgram(revenueUpdateDTO, programUrl, clientId, programId);
        return new ResponseEntity<>("Revenue Updated Successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Fetch revenue transaction report", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/report/fetchRevenueTransactions")
    public ResponseEntity<?> fetchRevenueTransactions(@RequestBody @Valid final TransactionBasedOnFiltersDTO transactionBasedOnFiltersDTO) {
        return revenueManagementService.getRevenueTransactionReport(transactionBasedOnFiltersDTO);
    }
}
