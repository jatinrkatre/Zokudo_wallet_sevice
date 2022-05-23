package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.PointBalanceDTO;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.dto.response.AuthResponseDTO;
import com.wallet.zokudo.entities.CustomerPointBalance;
import com.wallet.zokudo.services.PointBalanceService;
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
import java.util.Map;

@RestController
@RequestMapping("{programUrl}/api/v1/point")
public class PointBalanceController {

    private final PointBalanceService pointBalanceService;

    @Autowired
    public PointBalanceController(PointBalanceService pointBalanceService){
        this.pointBalanceService = pointBalanceService;
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "Point Accumulation w.rt. mobile number", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/collection", produces = "application/json")
    public ApiResponse updateCustomerPointBalance(@RequestBody PointBalanceDTO dto,
                                                  @PathVariable("programUrl") final String programUrl, AuthResponseDTO responseDTO) {
        return pointBalanceService.updateCustomerPointBalance(dto,programUrl,responseDTO);
    }

    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    @ApiOperation(value = "customer balance list", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/list", produces = "application/json")
    public Page<CustomerPointBalance> fetchPointsBalanceList(@RequestBody PointBalanceDTO dto,
                                                             @PathVariable("programUrl")final String programUrl) {
        return pointBalanceService.getCustomerBalanceList(dto,programUrl);
    }

    @ApiOperation(value = "download point balance list", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping(value ="/downloadPointBalanceList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void downloadPointBalanceList(HttpServletRequest request,HttpServletResponse response,
                                         @RequestParam final Map<String, String> requestParams) throws Exception{
    try{
        pointBalanceService.downloadpointBalanceList(response,requestParams);
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect(request.getHeader("Referer"));
         }
    }

    @ApiOperation(value = "Points ", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(origins = {"*"}, allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping(value ="/redeem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponse redeemPoints(@RequestBody PointBalanceDTO dto,
                                    @PathVariable("programUrl") final String programUrl) {
        return pointBalanceService.redeemPoints(dto,programUrl);
    }

}
