package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.MerchantCashbackRequestDTO;
import com.wallet.zokudo.dto.request.MerchantOfferDTO;
import com.wallet.zokudo.dto.response.EditOfferResponseDTO;
import com.wallet.zokudo.entities.MerchantCashbackConfig;
import com.wallet.zokudo.services.MerchantCashbackConfigService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("{programUrl}/api/v1/cashback")
public class MerchantCashbackConfigController {

    private final MerchantCashbackConfigService merchantCashbackConfigService;

    @Autowired
    public MerchantCashbackConfigController(final MerchantCashbackConfigService merchantCashbackConfigService){
        this.merchantCashbackConfigService = merchantCashbackConfigService;
    }

    @ApiOperation(value = "Configure Merchant Cashback", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/configureMerchantCashback")
    public ResponseEntity<?> configureMerchantCashback(@PathVariable("programUrl") final String programUrl,
                                             @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.configureCashbackAndBrandOffer(merchantCashbackRequestDTO, programUrl);
    }

    @ApiOperation(value = " edit program Configure Merchant Cashback", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/editProgram")
    public ResponseEntity<?> editconfigureMerchantCashback(@PathVariable("programUrl") final String programUrl,
                                                       @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.updateProgramCashbackOfferConfigurationDetails(merchantCashbackRequestDTO, programUrl);
    }

    @ApiOperation(value = "Fetch Merchant Cashback List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "fetchMerchantCashbackList")
    public ResponseEntity<?> fetchMerchantCashbackList(@PathVariable("programUrl") final String programUrl) {
        return merchantCashbackConfigService.fetchMerchantCashbackAndBrandOfferList();
    }

    @ApiOperation(value = "Update Cashaback Status", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "updateCashbackStatus")
    public ResponseEntity<?> updateCashbackStatus(@PathVariable("programUrl") final String programUrl,
                                                @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.updateCashBack(merchantCashbackRequestDTO);
    }

    @ApiOperation(value = "Update Cashaback offer config for add or remove terminals", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "updateOfferAndCashbackConfigByTerminalId")
    public ResponseEntity<?> updateOfferAndCashbackStatusByTerminal(@PathVariable("programUrl") final String programUrl,
                                                  @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.updateOfferAndCashbackStatusByTerminal(merchantCashbackRequestDTO);
    }

    @ApiOperation(value = "update cashback offer config for add or remove program Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "updateOfferAndCashbackByProgramId")
    public ResponseEntity<?> updateOfferAndCashbackByProgramId(@PathVariable("programUrl") final String programUrl,
                                                                 @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.updateOfferAndCashbackByProgramId(merchantCashbackRequestDTO);
    }

    @ApiOperation(value = "Update Cashaback Offer configuration details", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "updateCashbackOfferConfigurationDetails")
    public ResponseEntity<?> updateCashbackOfferConfigurationDetails(@PathVariable("programUrl") final String programUrl,
                                                                 @RequestBody @Valid final MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.updateCashbackOfferConfigurationDetails(merchantCashbackRequestDTO);
    }
    
    @ApiOperation(value = "list merchant cashback on filters", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/merchantCashBackListOnfilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Page<MerchantCashbackConfig>> getMerchantCashBackListOnfilters(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl,@RequestBody MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.getMerchantCashBackListOnFilters(merchantCashbackRequestDTO);
    }

    @ApiOperation(value = "list offers", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/getOfferList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<MerchantOfferDTO> getOfferList(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl,@RequestBody MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.getOfferList(merchantCashbackRequestDTO);
    }

    @ApiOperation(value = "Get offer by Offer ID", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @GetMapping(value = "/{offerId}/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EditOfferResponseDTO getOfferById(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl,@PathVariable("offerId") String offerID) {
        return merchantCashbackConfigService.getOfferById(offerID);
    }

    @ApiOperation(value = "terminate cashback offer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "/{offerId}/terminateCashbackOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> terminateCashbackOfferByOfferId(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl, @PathVariable("offerId") String offerID) {
        return merchantCashbackConfigService.terminateCashbackOfferByOfferId(offerID);
    }

   @ApiOperation(value = "list offers Based Filters", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/getOfferListBasedOnCondtion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<MerchantOfferDTO> getOfferListBasedOnFilter(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl,@RequestBody MerchantCashbackRequestDTO merchantCashbackRequestDTO) {
        return merchantCashbackConfigService.getOfferListBasedOnFilter(merchantCashbackRequestDTO);
    }

}
