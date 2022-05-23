package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.RequestDTO;
import com.wallet.zokudo.dto.request.DashboardRequestDTO;
import com.wallet.zokudo.repositories.TransactionRepository;
import com.wallet.zokudo.services.TransactionService;
import com.wallet.zokudo.services.report.ReportDTO;
import com.wallet.zokudo.services.report.TransactionReportInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("{programUrl}/api/v1")
public class TransactionApiController {

    private final TransactionReportInf transactionReportInf;
    private final TransactionService transactionService;

    TransactionApiController(TransactionReportInf transactionReportInf, TransactionService transactionService) {
        this.transactionReportInf = transactionReportInf;
        this.transactionService = transactionService;
    }


    @ApiOperation(value = "Get program transactions", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getTransactionReport", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getTransactionReport(HttpServletRequest request, @PathVariable("programUrl") String programUrl, @RequestBody ReportDTO reportDTO) {
        return transactionReportInf.getTransactionReport(programUrl, reportDTO, request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "Get transaction view", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getTransactionView", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getTransactionReport(@PathVariable("programUrl") String programUrl,
                                       @RequestBody ReportDTO reportDTO) {
        return transactionReportInf.getTransactionView(programUrl, reportDTO);
    }

    @ApiOperation(value = "download txn view", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/view/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            transactionReportInf.downloadTransactionReport(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "Set Default value for load card in transaction", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value = "/loadCard/setDefaultValue")
    public ResponseEntity<?> setDefaultValue(@RequestBody RequestDTO dto, @PathVariable("programUrl") String programUrl) {
        return transactionService.setDefaultforLoadCard(dto, programUrl);
    }

    @ApiOperation(value = "Get sum of transaction amount for dashboard", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value = "/getDashboardCountTransaction")
    public Object getDashboardCountTransaction(@Valid @RequestBody DashboardRequestDTO dashboardRequestDTO, @PathVariable("programUrl") String programUrl) {
        return transactionService.getDashboardCountTransaction(dashboardRequestDTO, programUrl);
    }

    @ApiOperation(value = "Host recon Transaction report", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/hostReconTransactionReport", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object hostReconTransactionReport(@PathVariable("programUrl") String programUrl,
                                       @RequestBody ReportDTO reportDTO) {
        return transactionReportInf.hostReconTransactionReport(programUrl, reportDTO);
    }

    @ApiOperation(value = "download Host recon Transaction report", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/view/downloadHostReconTransactionReport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadHostReconTransactionReport(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            transactionReportInf.downloadHostReconTransaction(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
