package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.VirtualAccountSetupRequest;
import com.wallet.zokudo.services.VirtualAccountService;
import com.wallet.zokudo.services.report.ReportDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/virtualAccount")
public class VirtualAccountController {

    private final VirtualAccountService virtualAccountService;

    @Autowired
    public VirtualAccountController(final VirtualAccountService virtualAccountService) {
        this.virtualAccountService = virtualAccountService;
    }

    @ApiOperation(value = "Save Virtual Account", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/saveVirtualAccount")
    public ResponseEntity<?> saveVirtualAccounts(@PathVariable("programUrl") final String programUrl,
                                                 @RequestBody @Valid VirtualAccountSetupRequest setupRequest) {
        return virtualAccountService.saveVirtualAccounts(setupRequest, programUrl);
    }

    @ApiOperation(value = "Get virtual accounts details", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getVirtualAccounts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getTransactionReport(HttpServletRequest request,
                                       @PathVariable("programUrl") String programUrl,
                                       @RequestBody ReportDTO reportDTO) {
        return virtualAccountService.getVirtualAccountsReport(reportDTO, request.getHeader("role"));
    }

    @ApiOperation(value = "download virtual account list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadVirtualAccountList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            virtualAccountService.downloadVirtualAccountReport(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
