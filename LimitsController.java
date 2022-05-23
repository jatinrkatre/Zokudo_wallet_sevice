package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.ChannelCardPrefDTO;
import com.wallet.zokudo.dto.request.ChannelLimitDTO;
import com.wallet.zokudo.dto.request.KycLimitsDTO;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.services.ChannelLimitService;
import com.wallet.zokudo.services.KycLimitsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1")
public class LimitsController {

    private final KycLimitsService kycLimitsService;
    private final ChannelLimitService channelLimitService;

    @Autowired
    public LimitsController(final KycLimitsService kycLimitsService,
                            final ChannelLimitService channelLimitService) {
        this.kycLimitsService = kycLimitsService;
        this.channelLimitService = channelLimitService;
    }

    @ApiOperation(value = "Set Kyc limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "kyc/setLimits")
    public ResponseEntity<?> setWalletLimits(@PathVariable("programUrl") final String programUrl,
                                             @RequestBody @Valid final KycLimitsDTO kycLimitsDto) {
        return kycLimitsService.setLimits(kycLimitsDto, programUrl);
    }

    @ApiOperation(value = "Fetch kyc limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "kyc/fetchLimits")
    public ResponseEntity<?> fetchCustomerKycLimits(@RequestHeader(value = "page") final int page,
                                                    @RequestHeader(value = "size") final int size,
                                                    @RequestHeader(value = "order") final String order) {
        return new ResponseEntity<>(kycLimitsService.fetchCustomerKycLimits(page, size, order), HttpStatus.OK);
    }

    @ApiOperation(value = "Fetch kyc limits by filters", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "kyc/getKYCLimitsByFilters")
    public ResponseEntity<?> getKYCLimitsByFilters(@RequestBody KycLimitsDTO kycLimitsDto,
                                                   @RequestHeader(value = "page") final int page,
                                                   @RequestHeader(value = "size") final int size,
                                                   @RequestHeader(value = "order") final String order) {
        return new ResponseEntity<>(kycLimitsService.getKYCLimitsByFilters(kycLimitsDto, page, size, order), HttpStatus.OK);
    }

    @ApiOperation(value = "Set channel limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/customer/{customerHashId}/setLimits")
    public ResponseEntity<?> setChannelLimits(@PathVariable("programUrl") final String programUrl,
                                              @PathVariable(value = "customerHashId") final String customerHashId,
                                              @RequestBody @Valid final ChannelLimitDTO channelLimitDTO) {
        return channelLimitService.setLimits(channelLimitDTO, programUrl, customerHashId);
    }

    @ApiOperation(value = "Update channel limit for add on", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/customer/addOnUpdateLimit")
    public ResponseEntity<?> updateChannelLimitForAddonCard(HttpServletRequest request,
                                                            @PathVariable("programUrl") final String programUrl,
                                                            @RequestBody ChannelLimitDTO channelLimitDTO) {
        return channelLimitService.updateChannelLimitForAddonCard(request.getHeader("programPlan"),channelLimitDTO);
    }

    @ApiOperation(value = "Set channel limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "channel/customer/{customerHashId}/updateLimits")
    public ResponseEntity<?> updateChannelLimits(HttpServletRequest request,
                                                 @PathVariable("programUrl") final String programUrl,
                                                 @PathVariable(value = "customerHashId") final String customerHashId,
                                                 @RequestBody @Valid final ChannelLimitDTO channelLimitDTO) {
        return channelLimitService.updateLimits(channelLimitDTO, programUrl, customerHashId, ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "Upgrade KYC Limit", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "kyc/upgradeLimits")
    public ResponseEntity<?> upgradeKycLimits(@RequestBody @Valid final KycLimitsDTO kycLimitsDto,
                                              @PathVariable("programUrl") final String programUrl) {
        return kycLimitsService.upgradeKycLimit(kycLimitsDto,programUrl);
    }

    @ApiOperation(value = "SET CHANNEL LIMITS BY CUSTOMER", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/setLimits")
    public ResponseEntity<?> setChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO,
                                              @PathVariable("programUrl") final String programUrl) {
        return channelLimitService.setLimits(channelLimitDTO, programUrl, channelLimitDTO.getCustomerHashId());
    }

    @ApiOperation(value = "SET CHANNEL TYPE BY CARD")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channelType/setLimits")
    public ResponseEntity<?> setChannelTypeByCard(@RequestBody final ChannelCardPrefDTO channelCardPrefDTO,
                                              @PathVariable("programUrl") final String programUrl) {
        return channelLimitService.setChannelTypeByProxyCardNumber(channelCardPrefDTO, programUrl);
    }

    @ApiOperation(value = "MIGRATION SETUP FOR CHANNEL TYPE CARD PREFERENCE. ")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "channelType/cardPreference/setup")
    public ResponseEntity<?> setChannelTypePreferenceByCardSetup(@PathVariable("programUrl") final String programUrl) {
        return channelLimitService.setChannelTypeCardSetupForMigration();
    }

    @ApiOperation(value = "GET CHANNEL TYPE PREFERENCE BY PROXY CARD")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "proxyCard/channelTypePreference")
    public ResponseEntity<?> getChannelTypePrefByProxyCard(@RequestBody final ChannelCardPrefDTO channelCardPrefDTO,
                                                  @PathVariable("programUrl") final String programUrl) {
        return channelLimitService.getChannelTypePrefByProxyCard(channelCardPrefDTO, programUrl);
    }

    @ApiOperation(value = "UPDATE CHANNEL SPENT LIMIT ")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channelSpent/update")
    public ResponseEntity<?> updateChannelSpentLimit(@RequestBody final ChannelCardPrefDTO channelCardPrefDTO) {
        return channelLimitService.updateChannelLimit(channelCardPrefDTO);
    }

    @ApiOperation(value = "GET CHANNEL SPENT LIMIT")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channelSpent/fetch")
    public ResponseEntity<?> getChannelSpentLimit(@RequestBody final ChannelCardPrefDTO channelCardPrefDTO) {
        return channelLimitService.getChannelLimit(channelCardPrefDTO);
    }
}
