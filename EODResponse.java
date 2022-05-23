package com.wallet.zokudo.dto;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class EODResponse {

	private BigInteger id;
	private double currentBalance;
	private String customerHashId;
	private String proxyCardNo;
	private String pocketName;
	private String createdDate;
	private BigInteger programId;
	private BigInteger clientId;
	private BigInteger walletId;
	private String transactionType;
	private String transactionRefNo;
	private String retrivalRefNo;
	private String comments;
	private double transactionAmt;
	private double billingAmt;
	private double cashBackAmt;
	private String status;
	private String updatedDate;
	private String cardType;
	private String expired;
	private String clientName;
	private String programName;
	private Date createdDateDownload;
	private Date updatedDateDownload;
	
}
