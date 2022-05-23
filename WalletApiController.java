package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.CashBackTransactionDTO;
import com.wallet.zokudo.dto.request.*;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.entities.BankTransaction;
import com.wallet.zokudo.entities.CashbackBankTransaction;
import com.wallet.zokudo.entities.MasterAccountTransactions;
import com.wallet.zokudo.entities.WalletLimits;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.services.*;
import com.wallet.zokudo.services.fetch.FetchInf;
import com.wallet.zokudo.services.persist.PersistInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1")
public class WalletApiController {

    private final FetchInf fetchInf;
    private final WalletLimitsService walletLimitsService;
    private final WalletService walletService;
    private final MasterTransactionsService masterTransactionsService;
    private final BankTransactionService bankTransactionService;
    private final CashbackBankTransactionService cashbackBankTransactionService;
    private final MerchantCashbackConfigService merchantCashbackConfigService;
    private final MerchantCashbackOffersService merchantCashbackOffersService;
    private final PointsLedgerService pointsLedgerService;

    @Autowired
    public WalletApiController(PersistInf persistInf,
                               FetchInf fetchInf,
                               final WalletLimitsService walletLimitsService,
                               final WalletService walletService,
                               final MasterTransactionsService masterTransactionsService,
                               final BankTransactionService bankTransactionService,
                               final CashbackBankTransactionService cashbackBankTransactionService,
                               final MerchantCashbackConfigService merchantCashbackConfigService,
                               final MerchantCashbackOffersService merchantCashbackOffersService,
                               final PointsLedgerService pointsLedgerService
    ) {
        this.fetchInf = fetchInf;
        this.walletLimitsService = walletLimitsService;
        this.walletService = walletService;
        this.masterTransactionsService = masterTransactionsService;
        this.bankTransactionService = bankTransactionService;
        this.cashbackBankTransactionService = cashbackBankTransactionService;
        this.merchantCashbackConfigService = merchantCashbackConfigService;
        this.merchantCashbackOffersService = merchantCashbackOffersService;
        this.pointsLedgerService = pointsLedgerService;
    }

   /* @ApiOperation(value = "Create new wallet", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/createWallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> persistWallet(HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return persistInf.execute(request, programUrl);
    }*/

    @ApiOperation(value = "Add Wallet", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/createWallet")
    public ApiResponse<?> addWallet(HttpServletRequest request,
                                    @RequestBody @Valid final AddWalletDTO walletDto) {
        return walletService.createWallet(request, walletDto.getClientId(), walletDto.getCustomerId(), walletDto.getProgramId(), walletDto.getCardType(), walletDto.getProgramPlan());
    }

    @ApiOperation(value = "fetch wallet details", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @GetMapping(value = "/getWallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> fetchWallet(HttpServletRequest request, @PathVariable("programUrl") String programUrl) {
        return fetchInf.execute(request, programUrl);
    }

    @ApiOperation(value = "Fetch all Wallet Limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @GetMapping(value = "/fetchAllWalletLimits")
    public List<WalletLimits> fetchAllWalletLimits(@PathVariable("programUrl") final String programUrl) {
        return walletLimitsService.getWalletLimits();
    }

    /**
     * @param request       - HttpServletRequest
     * @param programUrl
     * @param walletLoadDto
     * @return
     */
    @ApiOperation(value = "Load a wallet", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/loadWallet")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> walletLoad(HttpServletRequest request,
                                        @PathVariable("programUrl") final String programUrl,
                                        @RequestBody @Valid final WalletLoadDto walletLoadDto) {
        return walletService.walletLoad(walletLoadDto, programUrl, request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "Set limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "wallet/setLimits")
    public ResponseEntity<?> setWalletLimits(@PathVariable("programUrl") final String programUrl,
                                             @RequestBody @Valid final WalletLimitDTO walletLimits) {
        return walletLimitsService.setLimits(walletLimits, programUrl);
    }

    @ApiOperation(value = "Fetch wallet limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "wallet/fetchLimits")
    public ResponseEntity<?> fetchWalletLimits(@RequestHeader(value = "page") final int page,
                                               @RequestHeader(value = "size") final int size,
                                               @RequestHeader(value = "order") final String order) {
        return new ResponseEntity<>(walletLimitsService.fetchWalletLimits(page, size, order), HttpStatus.OK);
    }

    @ApiOperation(value = "Update limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PutMapping(value = "client/{clientId}/wallet/updateLimits")
    public ResponseEntity<?> updateWalletLimits(@PathVariable(value = "clientId") String clientId,
                                                @PathVariable("programUrl") final String programUrl,
                                                @RequestBody @Valid final WalletLimitDTO walletLimits) {
        return walletLimitsService.updateWalletLimits(clientId, walletLimits, programUrl);
    }

    @ApiOperation(value = "Fetch limits", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "client/{customerId}/wallet/fetchLimits")
    public ResponseEntity<?> fetchClientLimits(@PathVariable(value = "customerId") String customerId,
                                               @PathVariable("programUrl") final String programUrl) {
        return walletLimitsService.fetchCustomerLimitsByCustomerId(customerId, programUrl);
    }

    /**
     * @param clientId
     * @param customerHashId
     * @param walletHashId
     * @param programUrl
     * @param p2PTransferDTO
     * @return
     */
    @ApiOperation(value = "P2P transfer between customers within client", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/client/{clientId}/customer/{customerHashId}/wallet/{walletHashId}/p2pTransfer")
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    public ResponseEntity<?> p2pTransfer(@PathVariable(value = "clientId") final String clientId,
                                         @PathVariable(value = "customerHashId") final String customerHashId,
                                         @PathVariable(value = "walletHashId") final String walletHashId,
                                         @PathVariable("programUrl") final String programUrl,
                                         @RequestBody @Valid P2PTransferDTO p2PTransferDTO) {
        return walletService.p2PTransfer(clientId, walletHashId, customerHashId, p2PTransferDTO, programUrl);
    }

    @ApiOperation(value = "Fetch wallet", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @GetMapping(value = "customer/{customerHashId}/fetchWallet")
    public ResponseEntity<?> fetchWallet(@PathVariable(value = "customerHashId") final String customerHashId,
                                         @PathVariable("programUrl") final String programUrl) {
        return new ResponseEntity<>(walletService.fetchWallet(customerHashId, programUrl), HttpStatus.OK);
    }

    @ApiOperation(value = "Get program transactions", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getProgramTransactionBasedOnfilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getProgramTransactionsBasedOnfilters(HttpServletRequest request,
                                                       @PathVariable("programUrl") String programUrl,
                                                       @RequestBody TransactionBasedOnFiltersDTO transactionBasedOnFiltersDTO) {
        return walletService.getProgramTransactionBasedOnfilter(programUrl, transactionBasedOnFiltersDTO, request.getHeader("role"));
    }

    @ApiOperation(value = "download transaction list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadCardList(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("programUrl") String programUrl,
                                 @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            walletService.downloadTransactionList(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "download transaction list for client, program and card ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/general/downloadTransactionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadGenericTransactionList(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            walletService.downloadGenericTransactionList(request, response, programUrl, requestParams);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "get master account transaction ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value = "/mat/transactions")
    public Page<MasterAccountTransactions> getMasterAccTransactions(@RequestBody MasterAccountTransactionDTO dto) {
        return masterTransactionsService.getMasterAccountList(dto);
    }

    @ApiOperation(value = "get bank transaction ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value = "/bank/transactions")
    public Page<BankTransaction> getBankTransactions(@RequestBody BankTransactionDTO dto) {
        return bankTransactionService.getBankTransactions(dto);
    }

    @ApiOperation(value = "download master acc transaction ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @GetMapping(value = "/mat/downloadTransactionList")
    public void downloadMasterAccTransaction(HttpServletRequest request, HttpServletResponse response,
                                             @PathVariable("programUrl") String programUrl,
                                             @RequestParam final Map<String, String> requestParams) {
        masterTransactionsService.downloadMasterTransactionList(request, response, programUrl, requestParams);
    }

    @ApiOperation(value = "download bank transaction ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @GetMapping(value = "/bank/downloadTransactionList")
    public void downloadBankTransaction(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("programUrl") String programUrl,
                                        @RequestParam final Map<String, String> requestParams) {
        bankTransactionService.downloadBankTransactionList(request, response, programUrl, requestParams);
    }

    @ApiOperation(value = "get cashback txn ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value = "/cashback/transactions")
    public Page<CashbackBankTransaction> getCashBackTransactions(@RequestBody CashBackTransactionDTO dto) {
        return cashbackBankTransactionService.getAllTransactions(dto);
    }

    @ApiOperation(value = "download master acc transaction ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @GetMapping(value = "/cashback/downloadTransactionList")
    public void downloadCashbackTransaction(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable("programUrl") String programUrl,
                                            @RequestParam final Map<String, String> requestParams) {
        cashbackBankTransactionService.downloadCashBackTransactionReport(request, response, programUrl, requestParams);
    }

    /**
     * @param requestDTO
     * @param programUrl
     * @return total balance
     */
    @ApiOperation(value = "Fetch Card Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchCardBalance")
    public ResponseEntity<?> fetchCardBalance(@RequestBody @Valid final AppRequestDTO requestDTO,
                                              @PathVariable("programUrl") String programUrl) {
        return walletService.fetchCardBalance(programUrl, requestDTO);
    }

    @ApiOperation(value = "DashboardTransaction", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchDebitCount")
    public Long DashBoardDebitTransaction(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return walletService.DashBoardDebitTransaction(programUrl, request.getHeader("dateRange"), request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "DashboardTransaction", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchCreditCount")
    public Long DashBoardCreditTransaction(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return walletService.DashBoardCreditTransaction(programUrl, request.getHeader("dateRange"), request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "DashboardTransaction", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchTotalCreditAmount")
    public double DashBoardTotalCreditAmount(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return walletService.dashBoardTotalCreditAmount(programUrl, request.getHeader("dateRange"), request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "DashboardTransaction", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchTotalDebitAmount")
    public double DashBoardTotalDebitAmount(@PathVariable("programUrl") String programUrl, HttpServletRequest request) {
        return walletService.dashBoardTotalDebitAmount(programUrl, request.getHeader("dateRange"), request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }


    @ApiOperation(value = "Fetch Card Balance By MCC and Wallet Id", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchCardBalanceByMccAndWalletId")
    public ResponseEntity<?> fetchCardBalanceByProxyAndMccAndWalletId(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                                      @PathVariable("programUrl") String programUrl) {
        return walletService.fetchCardBalanceByMccAndWalletId(programUrl, requestDTO);
    }

    @ApiOperation(value = "fetch Kit Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/fetchKitBalance")
    public ResponseEntity<?> fetchKitBalance(@RequestBody @Valid final AppRequestDTO requestDTO,
                                             @PathVariable("programUrl") String programUrl) {
        return walletService.fetchKitBalance(programUrl, requestDTO);
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getCashbackOffers")
    public ResponseEntity<?> getCashbackOffers(@RequestBody @Valid final AppRequestDTO requestDTO,
                                               @PathVariable("programUrl") final String programUrl) {
        return merchantCashbackConfigService.getCashbackOffers(programUrl, requestDTO);
    }

    @ApiOperation(value = "Get transaction by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getCashbackHistoryByCustomer")
    public ResponseEntity<?> getCashbackHistoryByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                          @PathVariable("programUrl") final String programUrl) {
        return walletService.getCashbackHistoryByCustomer(programUrl, requestDTO);
    }

    @ApiOperation(value = "Fetch Total Wallet Balance", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchAllPocketBalancesByCustomer")
    public ResponseEntity<?> fetchAllPocketBalancesByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                              @PathVariable("programUrl") final String programUrl) {
        return walletService.fetchAllPocketBalancesByCustomer(programUrl, requestDTO, ProgramPlans.DEFAULT);
    }

    @ApiOperation(value = "wallet to wallet transfer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/walletToWalletTransfer")
    public ResponseEntity<?> walletToWalletTransfer(@RequestBody @Valid final WalletLoadDto walletLoadDto,
                                                    @PathVariable("programUrl") String programUrl) {
        return walletService.walletTowalletTransfer(programUrl, walletLoadDto);
    }

    @ApiOperation(value = "Fetch Total Cashback Balance by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/totalCashbackBalance")
    public ResponseEntity<?> fetchCashbackTotalBalanceByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                                 @PathVariable("programUrl") final String programUrl) {
        return walletService.fetchCashbackTotalBalanceByCustomer(programUrl, requestDTO);
    }

    @ApiOperation(value = "list merchant cashback offers by Mobile", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/merchantOffersByMobile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> merchantCashBackOffersByMobile(@RequestBody @Valid final AppRequestDTO requestDTO, @PathVariable("programUrl") String programUrl) {
        return merchantCashbackOffersService.merchantCashBackOffersByMobile(requestDTO, programUrl);
    }


    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "fetch customer point balance ledger based on Mobile number", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPointBalanceLedger(@RequestBody PointsLedgerDTO dto,
                                                   @PathVariable("programUrl") final String programUrl) {
        return pointsLedgerService.cashBackPointListBasedOnMobile(dto, programUrl);
    }

    @ApiOperation(value = "Get transaction by customer", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/getTransactionByCustomers")
    public ResponseEntity<?> getTransactionByCustomer(@RequestBody @Valid final AppRequestDTO requestDTO,
                                                      @PathVariable("programUrl") final String programUrl) {
        return new ResponseEntity<>(ApiResponse.builder().body(walletService.getTransactionByCustomer(programUrl, requestDTO, ProgramPlans.DEFAULT))
                .build(), HttpStatus.OK);
    }

    @ApiOperation(value = "GET POS/ATM TXN BY PROXY CARD NUMBER", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/getTransactionByTxnType")
    public ResponseEntity<?> getTransactionByTxnType(@RequestBody final WalletLoadDto dto,
                                                     @PathVariable("programUrl") final String programUrl) {
        return walletService.getTransactionByType(programUrl,dto);
    }
    
    @ApiOperation(value = "Fetch Total Wallet Balance by program", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
    @PostMapping(value = "/wallet/fetchTotalBalanceByProgram")
    public ResponseEntity<?> fetchTotalBalanceByProgram(@RequestBody @Valid final AppRequestDTO requestDTO,@PathVariable("programUrl") final String programUrl) {
        return walletService.fetchTotalBalanceByProgram(programUrl, requestDTO, ProgramPlans.DEFAULT);

    }
}
