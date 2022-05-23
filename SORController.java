package com.wallet.zokudo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.zokudo.dto.RequestDTO;
import com.wallet.zokudo.entities.TransactionView;
import com.wallet.zokudo.services.SORService;

@RestController
@RequestMapping("{programUrl}/api/v1/sor")
public class SORController {

	private final SORService sorService;
	@Autowired
	public SORController(final SORService sorService) {
		this.sorService = sorService;
	}
	
	@PostMapping(value="/transaction")
	public Object getIncrementalTransaction(@RequestBody RequestDTO dto) {
	
		return sorService.getIncrementalTransaction(dto.getStart(), dto.getEnd());
	}
}
