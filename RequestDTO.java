package com.wallet.zokudo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RequestDTO {

	private String dateRange;
	private String page;
	private String size;
	
	private Date startDate;
	private Date endDate;
	
	/**Following fields are declared for EOD Balance Report **/ 
	private String customerHashId;
	private String proxyCardNo;
	
	/** Following fields are used for setting default value of load card **/
	private String programId;
	private String clientId;
	private String cardHashId;
	
	private String start;
	private String end;
}
