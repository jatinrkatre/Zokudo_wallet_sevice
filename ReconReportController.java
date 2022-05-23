package com.wallet.zokudo.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallet.zokudo.dto.request.PrefundListBasedOnFiltersDTO;
import com.wallet.zokudo.dto.request.ReconReportBasedOnFiltersDTO;
import com.wallet.zokudo.services.recon.ReconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.zokudo.dto.request.TransactionBasedOnFiltersDTO;
import com.wallet.zokudo.services.WalletService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/recon/")
public class ReconReportController {

    private final WalletService walletService;
    private final ReconService reconService;

    @Autowired
    public ReconReportController(final WalletService walletService,
                                 final ReconService reconService) {
        this.walletService = walletService;
        this.reconService = reconService;
    }

    @ApiOperation(value = "General Recon Transaction (Card,Eod-Balance and Customer ledger) ", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getReports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getProgramTransactionsBasedOnfilters(HttpServletRequest request,
                                                       @PathVariable("programUrl") String programUrl,
                                                       @RequestBody TransactionBasedOnFiltersDTO transactionBasedOnFiltersDTO) {
        return walletService.getReconReport(programUrl, transactionBasedOnFiltersDTO, request.getHeader("role"));
    }


    @ApiOperation(value = "download recon transaction for CARD | EodBalance| Customer Leger  transasction report ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/generic/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            walletService.downloadGenericReconList(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


    @ApiOperation(value = "recon summary report ", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getReconSummaryReport", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getReconSummaryReportBasedOnfilters(HttpServletRequest request, @PathVariable("programUrl") String programUrl, @RequestBody ReconReportBasedOnFiltersDTO reconReportBasedOnFiltersDTO) {
        return reconService.getReconSummaryReport(programUrl, reconReportBasedOnFiltersDTO, request.getHeader("role"));
    }

    @ApiOperation(value = "download recon report view", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadReconSummaryReport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadReconSummaryReport(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("programUrl") String programUrl,
                                            @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            reconService.downloadReconSummaryReport(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }


    @ApiOperation(value = "Get Recon Report Based On Filters", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getReconReportBasedOnfilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getprefundListBasedOnfilters(HttpServletRequest request,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestBody ReconReportBasedOnFiltersDTO reconReportBasedOnFiltersDTO) {
        return reconService.execute(programUrl, reconReportBasedOnFiltersDTO);
    }

    @ApiOperation(value = "download recon report view", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/view/downloadReconReport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadReconReportViewList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            reconService.downloadReconReport(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
