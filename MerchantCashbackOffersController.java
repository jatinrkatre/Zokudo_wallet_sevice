package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.BrandOfferRequestDTO;
import com.wallet.zokudo.dto.request.MerchantCashbackRequestDTO;
import com.wallet.zokudo.entities.MerchantOffersConfig;
import com.wallet.zokudo.services.MerchantCashbackOffersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/merchant/")
public class MerchantCashbackOffersController {

    private final MerchantCashbackOffersService merchantCashbackOffersService;

    public MerchantCashbackOffersController(final MerchantCashbackOffersService merchantCashbackOffersService) {
        this.merchantCashbackOffersService = merchantCashbackOffersService;
    }

    /*@ApiOperation(value = "list merchant cashback offers by Mobile", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/merchantOffersByMobile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Page<MerchantOffersConfig>> merchantCashBackOffersByMobile(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl, @RequestBody String mobile) {
        return merchantCashbackOffersService.merchantCashBackOffersByMobile(programUrl, mobile);
    }*/

    @ApiOperation(value = "Configure Brand Offer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "configureBrandOffer")
    public ResponseEntity<?> configureBrandOffer(@PathVariable("programUrl") final String programUrl,
                                                 @RequestBody @Valid final BrandOfferRequestDTO brandOfferRequestDTO) {
        return merchantCashbackOffersService.configureBrandOffer(brandOfferRequestDTO, programUrl);
    }

    @ApiOperation(value = "Update Brand offer Status", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "updateBrandOfferStatus")
    public ResponseEntity<?> updateBrandOfferStatus(@PathVariable("programUrl") final String programUrl,
                                                    @RequestBody @Valid final MerchantCashbackRequestDTO brandOfferRequestDTO) {
        return merchantCashbackOffersService.updateBrandOffer(brandOfferRequestDTO);
    }

    @ApiOperation(value = "Fetch Brand offers List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "fetchBrandOffersList")
    public ResponseEntity<?> fetchBrandOffersList(@PathVariable("programUrl") final String programUrl) {
        return merchantCashbackOffersService.fetchBrandOffersList();
    }

    @ApiOperation(value = "list brand offers based on filters", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "brandOffersListOnfilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Page<MerchantOffersConfig>> getBrandOffersListOnfilter(HttpServletRequest request, HttpServletResponse
            response, @PathVariable("programUrl") String programUrl, @RequestBody BrandOfferRequestDTO brandOfferRequestDTO) {
        return merchantCashbackOffersService.getBrandOffersListOnfilter(brandOfferRequestDTO);
    }

    @ApiOperation(value = "list merchant cashback offers by Mobile", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/merchantOffersByMobile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> merchantCashBackOffersByMobile(@RequestBody @Valid final AppRequestDTO requestDTO,@PathVariable("programUrl") String programUrl) {
        return merchantCashbackOffersService.merchantCashBackOffersByMobile(requestDTO, programUrl);
    }
}
