package com.wallet.zokudo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.zokudo.dto.request.MarkUpDTO;
import com.wallet.zokudo.dto.response.ApiResponse;
import com.wallet.zokudo.entities.MarkUp;
import com.wallet.zokudo.services.MarkUpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("{programUrl}/api/v1")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = {"*"})
public class MarkUpController {

	private final MarkUpService markUpService;
	
	@Autowired
	public MarkUpController(MarkUpService markUpService) {
		this.markUpService = markUpService;
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "Adding Markup for MCC List")
	@PostMapping(value="/markup/add",produces = MediaType.APPLICATION_JSON_VALUE)
	ApiResponse<Object> addMarkUp(@ModelAttribute MarkUpDTO dto){
		return markUpService.addMarkUp(dto);
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "List of mcc with markups by pagination")
	@PostMapping(value = "/markups")
	Page<MarkUp> listOfMarkUps(@RequestBody MarkUpDTO dto){
		return markUpService.listOfMarkUps(dto);
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "Find markup for mcc By program type,program plan,mcc code and channel")
	@PostMapping(value ="/markup")
	MarkUp findMccMarkup(@RequestBody MarkUpDTO dto) {
		return markUpService.findMccMarkup(dto);
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "Remove the markup for mcc by markup ID")
	@DeleteMapping(value ="/{mccMarkUpId}/markup/delete")
	ResponseEntity<?> removeMccMarkUp(@PathVariable Long mccMarkUpId){
		return markUpService.removeMccMarkUp(mccMarkUpId);
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "List bulk upload summary for mcc markup")
	@PostMapping(value ="/markup/uploadSummary")
	Map<String, Object> uploadSummary(@RequestBody MarkUpDTO dto){
		return markUpService.uploadSummary(dto);
	}
	
	@ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value= "List bulk report for mcc markup")
	@PostMapping(value ="/markup/uploadReports")
	Map<String, Object> uploadReports(@RequestBody MarkUpDTO dto){
		return markUpService.uploadReports(dto);
	}
}
