package com.wallet.zokudo.controllers;

import com.wallet.zokudo.services.clientaccount.ClientAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("{programUrl}/api/v1")
public class ClientAccountController {

    private final ClientAccountService clientAccountService;

    @Autowired
    public ClientAccountController(final ClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @ApiOperation(value = "Create default client accounts", authorizations = {@Authorization(value = "basicAuth")})
    @PostMapping(value = "/wallet/client_account/add/default/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDefaultClientAccount(@PathVariable(value = "clientId") final String clientId, @PathVariable("programUrl") String programUrl) {
        return clientAccountService.addDefaultClientAccounts(Long.parseLong(clientId));
    }

    @ApiOperation(value = "Get client pool account balance", authorizations = {@Authorization(value = "basicAuth")})
    @GetMapping(value = "/wallet/client_account/{clientId}/accountBalance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClientAccountBalance(@PathVariable(value = "clientId") final String clientId, @PathVariable("programUrl") String programUrl) {
        return clientAccountService.getClientAccountBalance(clientId);
    }
}
