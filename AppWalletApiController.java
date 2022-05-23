package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.AppRequestDTO;
import com.wallet.zokudo.dto.request.BrandOfferRequestDTO;
import com.wallet.zokudo.dto.request.PointsLedgerDTO;
import com.wallet.zokudo.dto.request.WalletLoadDto;
import com.wallet.zokudo.dto.response.ApiError;
import com.wallet.zokudo.dto.response.CategoryWiseMerchantOffersRespDTO;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.services.MerchantCashbackConfigService;
import com.wallet.zokudo.services.MerchantCashbackOffersService;
import com.wallet.zokudo.services.PointsLedgerService;
import com.wallet.zokudo.services.WalletService;
import com.wallet.zokudo.services.cmsOffer.CMSOfferRecommendationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("appuser/api/v1")
public class AppWalletApiController {

    private final String APP_URL = "appuser";
    private final WalletService walletService;
    private final MerchantCashbackConfigService merchantCashbackConfigService;
    private final MerchantCashbackOffersService merchantCashbackOffersService;
    private final CMSOfferRecommendationService cmsOfferRecommendationService;
    private final PointsLedgerService pointsLedgerService;


    @Autowired
    public AppWalletApiController(final WalletService walletService,
                                  final MerchantCashbackConfigService merchantCashbackConfigService,
                                  final MerchantCashbackOffersService merchantCashbackOffersService,
                                  final CMSOfferRecommendationService cmsOfferRecommendationService,
                                  final PointsLedgerService pointsLedgerService) {
        this.walletService = walletService;
        this.merchantCashbackConfigService = merchantCashbackConfigService;
        this.merchantCashbackOffersService = merchantCashbackOffersService;
        this.cmsOfferRecommendationService = cmsOfferRecommendationService;
        this.pointsLedgerService = pointsLedgerService;
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchTotalBalance")
    public ResponseEntity<?> fetchWalletBalance(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.fetchTotalBalance(APP_URL, requestDTO, ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "Fetch Card Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchCardBalance")
    public ResponseEntity<?> fetchCardBalance(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.fetchCardBalance(APP_URL, requestDTO);
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getCashbackOffers")
    public ResponseEntity<?> getCashbackOffers(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return merchantCashbackConfigService.getCashbackOffers(APP_URL, requestDTO);
    }

    @ApiOperation(value = "Get transaction by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getTransactionByCustomer")
    public ResponseEntity<?> getTransactionByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO) {
        ApiError response = new ApiError(HttpStatus.OK, "Transaction Summary", walletService.getTransactionByCustomer(APP_URL, requestDTO, ProgramPlans.DEFAULT).get("txnDetails"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get transaction by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getCashbackHistoryByCustomer")
    public ResponseEntity<?> getCashbackHistoryByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.getCashbackHistoryByCustomer(APP_URL, requestDTO);
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchAllPocketBalancesByCustomer")
    public ResponseEntity<?> fetchAllPocketBalancesByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.fetchAllPocketBalancesByCustomer(APP_URL, requestDTO, ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "Fetch Total Cashback Balance by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/totalCashbackBalance")
    public ResponseEntity<?> fetchCashbackTotalBalanceByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return walletService.fetchCashbackTotalBalanceByCustomer(APP_URL, requestDTO);
    }

    @ApiOperation(value = "list merchant cashback offers by Mobile", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/merchantOffersByMobile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> merchantCashBackOffersByMobile(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return merchantCashbackOffersService.merchantCashBackOffersByMobile(requestDTO, APP_URL);
    }


    //temp API wrapper just for testing purpose
    @ApiOperation(value = "get AI ML offer recommendations", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/getOfferRecommendation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryWiseMerchantOffersRespDTO> getOfferRecommendation(@RequestBody @Valid final AppRequestDTO requestDTO) {
        return cmsOfferRecommendationService.getAIMLRecommendation(Long.valueOf(requestDTO.getMobileNumber()));
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "fetch customer point balance ledger based on Mobile number", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPointBalanceLedger(@RequestBody PointsLedgerDTO dto) {
        return pointsLedgerService.cashBackPointListBasedOnMobile(dto, APP_URL);
    }

    @ApiOperation(value = "list brand cashback offers by brand id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/cashbackOffersByBrandId", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cashbackOffersByBrandId(@RequestBody @Valid final BrandOfferRequestDTO apiRequestDto) {
        return merchantCashbackOffersService.cashbackOffersByBrandId(apiRequestDto, APP_URL);
    }

    @ApiOperation(value = "GET POS/ATM TXN BY PROXY CARD NUMBER", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/getTransactionByTxnType")
    public ResponseEntity<?> getTransactionByTxnType(@RequestBody final WalletLoadDto dto) {
       return walletService.getTransactionByType(APP_URL,dto);
    }
}
