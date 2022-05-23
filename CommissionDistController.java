package com.wallet.zokudo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wallet.zokudo.dto.request.CommissionUpdateDTO;
import com.wallet.zokudo.services.CommissionDistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1/commmission/distributor")
public class CommissionDistController {

	private final CommissionDistService commissionService;
	
	@Autowired
	public CommissionDistController(CommissionDistService commissionService) {
		this.commissionService = commissionService;
	}
	
	@ApiOperation(value="set default commission for on-boarding program" ,authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
	@GetMapping("/addDefaultComm")
	public ResponseEntity<?> addDefaultComm(@RequestHeader long distributorId,@RequestHeader long programId) {
		commissionService.addDefaultCommission(programId, distributorId);
		return new ResponseEntity<>("Default commission added for the program", HttpStatus.OK);
	}
	
	@ApiOperation(value="fetch all commission by distributor Id",authorizations = {@Authorization(value = "basicAuth")})
	@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
	@GetMapping(value = "/getDistComm", produces = MediaType.APPLICATION_JSON_VALUE)
	  public Map getCommissionListByDistributor(@RequestHeader("distributorId") String distributorId,
              @RequestHeader("page") String page,
              @RequestHeader("size") String size,
              @RequestHeader("order") String order) {
	return commissionService.fetchCommissionByDistributorId(Integer.parseInt(page), Integer.parseInt(size), order, distributorId);
} 
	  	
		@ApiOperation(value = "update commission for distributor", authorizations = {@Authorization(value = "basicAuth")})
	    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
	    @PutMapping(value = "/updateDistCommission", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> updateRevenueValuesByDistributor(@RequestBody CommissionUpdateDTO CommissionUpdateDTO,
	                                                       @PathVariable("programUrl") final String programUrl,
	                                                       @RequestHeader("distributorId") String distributorId,
	                                                       @RequestHeader("programId") String programId) {
			return commissionService.updateCommissionForDistributor(CommissionUpdateDTO, programUrl, distributorId, programId);
	        
	    }
	
}
