package com.wallet.zokudo.controllers;

import com.wallet.zokudo.dto.request.ClientPrefundRequestDTO;
import com.wallet.zokudo.services.PrefundRequestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("{programUrl}/api/v1/agent")
public class AgentPrefundController {

    private final PrefundRequestService prefundRequestService;

    @Autowired
    public AgentPrefundController(final PrefundRequestService prefundRequestService) {
        this.prefundRequestService = prefundRequestService;
    }

    @ApiOperation(value = "prefund request client account without image", authorizations = {@Authorization(value = "basicAuth")})
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = RequestMethod.POST, origins = {"*"})
    @PostMapping(value = "/prefund")
    public ResponseEntity<?> savePrefundRequest(HttpServletRequest request, @PathVariable("programUrl") final String programUrl,
                                                @RequestBody final ClientPrefundRequestDTO clientPrefundRequestDTO) {
        return prefundRequestService.prefundClientRequest(clientPrefundRequestDTO, request.getHeader("loggedInUserHashId"), programUrl, request.getHeader("role"));
    }


}
