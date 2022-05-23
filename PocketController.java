package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.MCCDataDTO;
import com.wallet.zokudo.dto.request.PocketDto;
import com.wallet.zokudo.dto.response.ApiError;
import com.wallet.zokudo.entities.MccData;
import com.wallet.zokudo.services.PocketDefinitionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/wallet")
public class PocketController {

    private final PocketDefinitionService pocketDefinitionService;

    @Autowired
    public PocketController(final PocketDefinitionService pocketDefinitionService) {
        this.pocketDefinitionService = pocketDefinitionService;
    }

    @PostMapping(value = "/addDefaultPocket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDefaultPocket(@RequestBody @Valid final MCCDataDTO mccData) {
        return pocketDefinitionService.createDefaultPocket(mccData);
    }
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Fetch all the pockets by client", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/listPockets", produces = "application/json")
    public ResponseEntity<?> listPocketsByClient(@RequestHeader("programId") long programId) {
        return new ResponseEntity<>(pocketDefinitionService.getAllByProgramId(programId), HttpStatus.OK);
    }

    @ApiOperation(value = "Fetch all mcc codes", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @GetMapping(value = "/fetchAllMccCategories")
    public List<MccData> fetchAllMccCategories(@PathVariable("programUrl") final String programUrl) {
        return pocketDefinitionService.getMccCodes(programUrl);
    }
    
    @ApiOperation(value = "add pocket", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/addPocket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiError addPocket(@PathVariable("programUrl") String programUrl, @RequestBody PocketDto pocketdto) {
        return pocketDefinitionService.createPocket(pocketdto, programUrl);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Update pocket max limit", authorizations = {@Authorization(value = "basicAuth")})
    @PutMapping(value = "/updatePocketMaxLimit", produces = "application/json")
    public ResponseEntity<?> updatePocketMaxLimit(@RequestHeader("maxLimit") String maxLimit,
                                                  @RequestHeader("pocketId") String pocketId) {
        return pocketDefinitionService.updatePocketMaxLimit(maxLimit, pocketId);
    }
}
