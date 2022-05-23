package com.wallet.zokudo.controllers;


import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.FeeDeductionDto;
import com.wallet.zokudo.dto.request.FeeUpdateRequestDTO;
import com.wallet.zokudo.dto.request.TransactionReportDTO;
import com.wallet.zokudo.services.FeeManagementService;
import com.wallet.zokudo.services.TransactionService;
import com.wallet.zokudo.services.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/feemanagement")
public class FeeManagementController {

    private final FeeManagementService feeManagementService;
    private final WalletService walletService;
    private final TransactionService transactionService;

    @Autowired
    public FeeManagementController(final FeeManagementService feeManagementService,
                                   final WalletService walletService,
                                   final TransactionService transactionService) {
        this.feeManagementService = feeManagementService;
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "add default fees", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/adddefault")
    public ResponseEntity<?> addDefaultFeeValuesAtProgramSetUp(@RequestHeader("clientId") Long clientId, @RequestHeader("programId") Long programId) {
        feeManagementService.addDefaultFeesForProgram(clientId, programId);
        return new ResponseEntity<>("Default fees added for the client", HttpStatus.OK);
    }

    @ApiOperation(value = "update fees for program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/updatefees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFeesValuesByProgram(@RequestBody FeeUpdateRequestDTO feeUpdateRequestDTO,
                                                       @PathVariable("programUrl") final String programUrl,
                                                       @RequestHeader("clientId") String clientId,
                                                       @RequestHeader("programId") String programId) {
        feeManagementService.updateFeesForProgram(feeUpdateRequestDTO, programUrl, clientId, programId);
        return new ResponseEntity<>("Fee Updated Successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "get all fees by programId", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/fees", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public Map getFeesListByProgram(@RequestHeader("programId") String programId,
                                    @RequestHeader("page") String page,
                                    @RequestHeader("size") String size,
                                    @RequestHeader("order") String order) {
        return feeManagementService.fetchFeesByProgramId(Integer.parseInt(page), Integer.parseInt(size), order, programId);
    }

    @ApiOperation(value = "Fee Debit", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/DebitFee")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> debitFee(@RequestBody @Valid final FeeDeductionDto feeDeductionDto) {
        return walletService.feeDeduction(feeDeductionDto);
    }

    @ApiOperation(value = "Fetch fee transaction report", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/report/fetchFeeTransactions")
    public ResponseEntity<?> fetchFeeTransactions(HttpServletRequest request,@RequestBody @Valid final TransactionReportDTO transactionReportDTO,@PathVariable String programUrl) {
        return transactionService.getFeeTransactionReport(transactionReportDTO,request.getHeader("role"),request.getHeader("category"),programUrl,request.getHeader("loggedInUserHashId"));
    }
    
    @ApiOperation(value = "download fee transaction list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadCardList(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("programUrl") String programUrl,
                                 @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
        	walletService.downloadFeeTransactionList(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    
    
}
