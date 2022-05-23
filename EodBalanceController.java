package com.wallet.zokudo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.zokudo.dto.EODResponse;
import com.wallet.zokudo.dto.RequestDTO;
import com.wallet.zokudo.services.EodBalanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/report/eodbalance")
public class EodBalanceController {


    private final EodBalanceService eodBalanceService;

    @Autowired
    public EodBalanceController(EodBalanceService eodBalanceService) {
        this.eodBalanceService = eodBalanceService;
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @ApiOperation(value = "fetch all eod balance old", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping("/allrecordsOld")
    public List<EODResponse> getAllTxn(@RequestBody RequestDTO dto) {
        return eodBalanceService.getEodBalanceReport(dto);
    }

    @ApiOperation(value = "download eod txn", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            eodBalanceService.downloadEodBalanceReport(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @ApiOperation(value = "fetch all eod balance", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping("/allrecords")
    public List<EODResponse> getEodBalanceReportNew(@RequestBody RequestDTO dto,
                                                    @PathVariable("programUrl") String programUrl) {
        return eodBalanceService.getEodBalanceReportNew(dto, programUrl);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @ApiOperation(value = "Save EOD Data By Date Range", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping("/saveEodDataByDateRange")
    public ResponseEntity<?> saveEodDataByDateRance(@RequestBody RequestDTO requestDTO,
                                                 @PathVariable("programUrl") String programUrl) {
        return eodBalanceService.setupEodReportDataByDate(requestDTO, programUrl);
    }

    @ApiOperation(value = "download eod txn", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadTransactionListNew", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionListNew(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            eodBalanceService.downloadEodBalanceReportNew(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @ApiOperation(value = "get list of EOD report by Date", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping("/getEODReportList")
    public ResponseEntity<?> getEODReportList(@RequestBody RequestDTO requestDTO,
                                                    @PathVariable("programUrl") String programUrl) {
        return eodBalanceService.getEODReportList(requestDTO, programUrl);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @ApiOperation(value = "Generate daily EOD report", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping("/generateDailyEODReport")
    public ResponseEntity<?> generateDailyEODReport(@PathVariable("programUrl") String programUrl) {
        return eodBalanceService.generateDailyEODReport(new Date());
    }
}
