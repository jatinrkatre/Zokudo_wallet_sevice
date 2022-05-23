package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.BrandOfferRequestDTO;
import com.wallet.zokudo.dto.request.NDCLimitDTO;
import com.wallet.zokudo.entities.NDCSetup;
import com.wallet.zokudo.services.ndc.NDCLimitService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("{programUrl}/api/v1/ndclimit/")
public class NDCLimitController {

    private final NDCLimitService ndcLimitService;

    @Autowired
    public NDCLimitController(NDCLimitService ndcLimitService){
        this.ndcLimitService = ndcLimitService;

    }

    @ApiOperation(value = "Setup NDC Cycle and its Max Limit", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "setup")
    public ResponseEntity<?> setupNDCCycle(@RequestBody @Valid final NDCLimitDTO limitDTO) {
        return ndcLimitService.setupNDCCycle(limitDTO);
    }

    @ApiOperation(value = "Get NDC Cycles", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "cycleList")
    public List<NDCSetup> getNDCCycles() {
        return ndcLimitService.getNDCCycle();
    }

    @ApiOperation(value = "Update NDC Cycles", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "updateCycle")
    public ResponseEntity<?> updateNDCCycles(@RequestBody @Valid NDCLimitDTO dto) {
        return ndcLimitService.updateNDCCycle(dto);
    }

    @ApiOperation(value = "Update NDC Cycles", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "getNDCCyclesByRetailer")
    public List<NDCSetup> updateNDCCycles(@RequestHeader String retailerHashId) {
        return ndcLimitService.getNDCCycleByRetailer(retailerHashId);
    }

    @ApiOperation(value = "Update NDC Cycles", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "setNDCLimit")
    public ResponseEntity<?> setNDCLimit(@RequestBody NDCLimitDTO dto) {
        return ndcLimitService.setNDCLimit(dto);
    }

    @ApiOperation(value = "Test", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "test")
    public void test() {
         ndcLimitService.test();
    }
}
