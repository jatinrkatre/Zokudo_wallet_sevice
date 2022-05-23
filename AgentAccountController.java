package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.AccountPoolBalanceDTO;
import com.wallet.zokudo.services.agentaccount.AgentAccountInf;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("{programUrl}/api/v1/wallet")
public class AgentAccountController {

    private final AgentAccountInf agentAccount;

    public AgentAccountController(AgentAccountInf agentAccount) {
        this.agentAccount = agentAccount;
    }

    @ApiOperation(value = "agent account", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/openAgentAccount")
    public ResponseEntity<?> agentAccount(HttpServletRequest request, @PathVariable("programUrl") final String programUrl) {
        return agentAccount.addDefaultAgentAccounts(request.getHeader("agentId"), request.getHeader("clientId"), programUrl);
    }

    @ApiOperation(value = "Get list of superdistributor/distributor/agents and pool account balances based on filter", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getListOfPoolBalances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClientAccountBalance(HttpServletRequest request, @PathVariable("programUrl") final String programUrl) {
        return agentAccount.getListOfPoolBalance(request.getHeader("role"), programUrl, request.getHeader("loginUserHashId"));
    }

    @ApiOperation(value = "Get total pool account balance by superDistributor/distributor/agent", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.GET, origins = {"*"})
    @GetMapping(value = "/getTotalPoolBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalPoolBalance(HttpServletRequest request, @PathVariable("programUrl") final String programUrl) {
        return agentAccount.getTotalPoolBalance(request.getHeader("role"), programUrl, request.getHeader("clientId"), request.getHeader("loginUserHashId"));
    }

}
