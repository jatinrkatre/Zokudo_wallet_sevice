package com.wallet.zokudo.controllers;


import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.ChannelLimitDTO;
import com.wallet.zokudo.dto.request.KycLimitsDTO;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.services.ChannelLimitService;
import com.wallet.zokudo.services.KycLimitsService;
import com.wallet.zokudo.services.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("appuser/api/v1")
public class AppLimitsApiController {

    private final String APP_URL = "appuser";
    private final KycLimitsService kycLimitsService;
    private final ChannelLimitService channelLimitService;
    private final WalletService walletService;

    @Autowired
    public AppLimitsApiController(final KycLimitsService kycLimitsService,
                                  final ChannelLimitService channelLimitService,
                                  final WalletService walletService) {
        this.kycLimitsService = kycLimitsService;
        this.channelLimitService = channelLimitService;
        this.walletService = walletService;
    }

    @ApiOperation(value = "Upgrade KYC Limit", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "/kyc/upgradeLimits")
    public ResponseEntity<?> upgradeKycLimits(@RequestBody @Valid final KycLimitsDTO kycLimitsDto) {
        return kycLimitsService.upgradeKycLimit(kycLimitsDto,APP_URL);
    }

    @ApiOperation(value = "Set channel limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/setLimits")
    public ResponseEntity<?> setChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO
                                              ) {
        return channelLimitService.setLimits(channelLimitDTO, APP_URL, channelLimitDTO.getCustomerHashId());
    }

    @ApiOperation(value = "Update limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/updateLimits")
    public ResponseEntity<?> updateChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO) {
        return channelLimitService.updateLimits(channelLimitDTO, APP_URL, channelLimitDTO.getCustomerHashId(), ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "Fetch limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/fetchLimits")
    public ResponseEntity<?> fetchChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO) {
        return channelLimitService.fetchLimitsByCustomer(channelLimitDTO, APP_URL, channelLimitDTO.getCustomerHashId());
    }

    @ApiOperation(value = "Fetch kyc remaining limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "kyc/remainingLimits")
    public ResponseEntity<?> kycRemainingLimits(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.fetchKycRemainingLimits(APP_URL, requestDTO, ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "Fetch Spent Limit By Customer ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchSpentLimit")
    public ResponseEntity<?> fetchSpentLimitByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.spentLimitsByCustomer(APP_URL, requestDTO);
    }
}
