package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.PointsCashBackConfigDTO;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.entities.PointsConversionrateConfig;
import com.wallet.zokudo.services.ConversionRateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/coversionRate")
public class ConversionRateController {

    private final ConversionRateService conversionRateService;

    @Autowired
    public ConversionRateController(ConversionRateService conversionRateService) {
        this.conversionRateService = conversionRateService;
    }

    @ApiOperation(value = "Saving conversionRate with program id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value="/ConversionRateCashbackConfig")
    public ResponseEntity<?> conversionRateCashbackConfig(@RequestBody @Valid final PointsCashBackConfigDTO pointsCashBackConfigDTO,
                                                          @PathVariable("programUrl") final String programUrl){
        return conversionRateService.conversionRateWithProgramsId(pointsCashBackConfigDTO,programUrl);
    }
    @ApiOperation(value = "list ConversionRate", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/getConversionRateList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<PointsConversionrateConfig> getConversionRate(@RequestBody @Valid final PointsCashBackConfigDTO pointsCashBackConfigDTO){
        return conversionRateService.getConversionRateDetals(pointsCashBackConfigDTO);
    }
    @ApiOperation(value = "Update ConversionRate Status", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/UpdateConversionRateStatus")
    public ResponseEntity<?> updateConversionRateStatus(@PathVariable("programUrl") final String programUrl,
                                                  @RequestBody @Valid final PointsCashBackConfigDTO pointsCashBackConfigDTO) {
        return conversionRateService.updateConversionRateStatus(pointsCashBackConfigDTO);
    }


    @ApiOperation(value = "Update ConversionRate", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/UpdateConversionRate")
    public ResponseEntity<?> updateConversionRate(@PathVariable("programUrl") final String programUrl,
                                            @RequestBody @Valid final PointsCashBackConfigDTO pointsCashBackConfigDTO) {
       return conversionRateService.editConversionRate(pointsCashBackConfigDTO);
    }

    @ApiOperation(value = "ConversionRate by programId", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/conversionRateByProgramId", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Object> getConversionRateByProgram(@RequestBody @Valid final PointsCashBackConfigDTO pointsCashBackConfigDTO){
       return conversionRateService.getConversionRateByprogramId(pointsCashBackConfigDTO);
    }
}
