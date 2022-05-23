package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.ClientPrefundRequestDTO;
import com.wallet.zokudo.dto.request.ClientPrefundUpdateRequest;
import com.wallet.zokudo.dto.request.IECTransactionRequest;
import com.wallet.zokudo.dto.request.PrefundListBasedOnFiltersDTO;
import com.wallet.zokudo.exceptions.BizException;
import com.wallet.zokudo.services.PrefundListBasedOnFilters;
import com.wallet.zokudo.services.PrefundRequestService;
import com.wallet.zokudo.services.collectionapi.CollectionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1")
public class ClientPrefundController {

    private final PrefundRequestService prefundRequestService;
    private final PrefundListBasedOnFilters prefundListBasedOnFilters;
    private final CollectionService collectionService;


    @Autowired
    public ClientPrefundController(final PrefundRequestService prefundRequestService,
                                   final PrefundListBasedOnFilters prefundListBasedOnFilters,
                                   final CollectionService collectionService) {
        this.prefundRequestService = prefundRequestService;
        this.prefundListBasedOnFilters = prefundListBasedOnFilters;
        this.collectionService = collectionService;
    }

    @ApiOperation(value = "prefund request client account without image", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/client/{clientHashId}/prefund")
    public ResponseEntity<?> savePrefundRequest(HttpServletRequest request, @PathVariable(value = "clientHashId") final String clientHashId,
                                                @PathVariable("programUrl") final String programUrl,
                                                @RequestBody @Valid final ClientPrefundRequestDTO clientPrefundRequestDTO) {
        return prefundRequestService.prefundClientRequest(clientPrefundRequestDTO, clientHashId, programUrl, request.getHeader("role"));
    }

    @ApiOperation(value = "Fetch prefund request", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/client/prefundList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> fetchPrefundRequests(@RequestHeader(value = "page") final String page,
                                                  @RequestHeader(value = "size") final String size,
                                                  @RequestHeader(value = "order") final String order,
                                                  @PathVariable("programUrl") final String programUrl,
                                                  @RequestHeader("role") final String role,
                                                  @RequestHeader("loggedInUserHashId") final String loggedInUserHashId) {
        return new ResponseEntity<>(prefundRequestService.getAllPrefundRequests(page, size, order, programUrl, role, loggedInUserHashId), HttpStatus.OK);
    }

    @ApiOperation(value = "Approve/Reject client prefund request", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.PUT, origins = {"*"})
    @PutMapping(value = "/client/{clientId}/updatePrefundRequest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updatePrefundStatus(HttpServletRequest request, @PathVariable(value = "clientId") final String clientId,
                                                 @PathVariable("programUrl") final String programUrl,
                                                 @RequestBody @Valid final ClientPrefundUpdateRequest clientPrefundUpdateRequest) {
        return new ResponseEntity<>(prefundRequestService.updatePrefundStatus(clientId, clientPrefundUpdateRequest, programUrl,
                request.getHeader("role"), request.getHeader("loggedInUserHashId")), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Prefund List Based On Filters", authorizations = {@Authorization("basicAuth")})
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true", origins = {"*"})
    @PostMapping(value = "/getPrefundListBasedOnfilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getprefundListBasedOnfilters(HttpServletRequest request,
                                               @PathVariable("programUrl") String programUrl,
                                               @RequestBody PrefundListBasedOnFiltersDTO prefundListBasedOnFiltersDTO) {
        return prefundListBasedOnFilters.execute(programUrl, prefundListBasedOnFiltersDTO, request.getHeader("role"), request.getHeader("loggedInUserHashId"));
    }

    @ApiOperation(value = "download Prefund List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadPrefundList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadPrefundList(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("programUrl") String programUrl,
                                    @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            prefundRequestService.downloadPrefundList(request, response, programUrl, requestParams);
        } catch (BizException e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "download Prefund List", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value = "/downloadDailyPrefundList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadDailyPrefundList(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable("programUrl") String programUrl,
                                         @RequestParam final Map<String, String> requestParams) throws Exception {
        try {
            prefundRequestService.downloadDailyPrefundList(request, response, programUrl, requestParams);
        } catch (BizException e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    @ApiOperation(value = "save prefund request by admin", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/client/savePrefundRequestByAdmin")
    public ResponseEntity<?> savePrefundRequestByAdmin(HttpServletRequest request,
                                                       @PathVariable("programUrl") final String programUrl,
                                                       @RequestBody @Valid final IECTransactionRequest iecTransactionRequest) {
        return collectionService.savePrefundRequest(iecTransactionRequest);
    }
}
