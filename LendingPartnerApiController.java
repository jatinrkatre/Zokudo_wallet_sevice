package com.wallet.zokudo.controllers;


import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.ChannelLimitDTO;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.services.ChannelLimitService;
import com.wallet.zokudo.services.WalletService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
//@RequestMapping("lending/api/v1")
@RequestMapping("{programUrl}/api/v1")
public class LendingPartnerApiController {

    private final String APP_URL = "lending";
    private final ChannelLimitService channelLimitService;
    private final WalletService walletService;

    public LendingPartnerApiController(final ChannelLimitService channelLimitService,
                                       final WalletService walletService) {
        this.channelLimitService = channelLimitService;
        this.walletService = walletService;
    }

    @ApiOperation(value = "Update limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/updateLimits")
    public ResponseEntity<?> updateChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO,
                                                 @PathVariable("programUrl") final String programUrl) {
        return channelLimitService.updateLimits(channelLimitDTO, programUrl, channelLimitDTO.getCustomerHashId(), ProgramPlans.LENDING);
    }

    @ApiOperation(value = "Fetch limits by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "channel/fetchLimits")
    public ResponseEntity<?> fetchChannelLimits(@RequestBody @Valid final ChannelLimitDTO channelLimitDTO,
                                                @PathVariable("programUrl") final String programUrl) {
        return channelLimitService.fetchLimitsByCustomer(channelLimitDTO, programUrl, channelLimitDTO.getCustomerHashId());
    }

    @ApiOperation(value = "Fetch kyc remaining limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "kyc/remainingLimits")
    public ResponseEntity<?> kycRemainingLimits(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                @PathVariable("programUrl") final String programUrl) {
        return walletService.fetchKycRemainingLimits(programUrl, requestDTO, ProgramPlans.LENDING);
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchTotalBalance")
    public ResponseEntity<?> fetchWalletBalance(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                @PathVariable("programUrl") final String programUrl) {
        return walletService.fetchTotalBalance(programUrl, requestDTO, ProgramPlans.LENDING);
    }

    @ApiOperation(value = "Fetch Card Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchCardBalance")
    public ResponseEntity<?> fetchCardBalance(@RequestBody @Valid final AppRequestDTO requestDTO,
                                              @PathVariable("programUrl") final String programUrl) {
        return walletService.fetchCardBalance(programUrl, requestDTO);
    }

    @ApiOperation(value = "Get transaction by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getTransactionByCustomer")
    public ResponseEntity<?> getTransactionByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                      @PathVariable("programUrl") final String programUrl) {
        return new ResponseEntity<>(ApiResponse.builder().
                body(walletService.getTransactionByCustomer(programUrl, requestDTO, ProgramPlans.LENDING)).build(), HttpStatus.OK);
    }
}
