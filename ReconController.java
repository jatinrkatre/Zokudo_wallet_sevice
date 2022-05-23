package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.ReconRequestDTO;
import com.wallet.zokudo.dto.request.TransactionBasedOnFiltersDTO;
import com.wallet.zokudo.services.WalletService;
import com.wallet.zokudo.services.recon.ReconService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("{programUrl}/api/v1/reconservice")
public class ReconController {
    private final ReconService reconService;

    @Autowired
    public ReconController(final ReconService reconService) {
        this.reconService = reconService;
    }


    @ApiOperation(value = "upload recon file to surver - testing purpose only", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/reconFileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object reconFileUpload(HttpServletRequest request, @PathVariable("programUrl") String programUrl, @ModelAttribute ReconRequestDTO reconRequestDTO) {
        return reconService.reconFileUpload(programUrl, request.getHeader("role"), reconRequestDTO);
    }

    @ApiOperation(value = "process transaction recon", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/transactionRecon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object doTransactionRecon(HttpServletRequest request,
                                     @PathVariable("programUrl") String programUrl,
                                     @ModelAttribute ReconRequestDTO reconRequestDTO) {
        return reconService.doTransactionRecon(programUrl, request.getHeader("role"), reconRequestDTO);
    }
}
