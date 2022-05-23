package com.wallet.zokudo.dto.request;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.enums.Status;

import lombok.Data;

@Data
public class MarkUpDTO {

	private MultipartFile mccSheetId;
	private String orginalFileName;
	private String fileName;
	private String isBulk;
	 
	private String mccName;
	private String mccCode;
	private List<String> mccGroupCodes;
	private String channel;
	private double value;
	private String isFixed;
	private String dateRange;
	private Status status;
	private String size;
	private String page;
	private ProgramPlans programPlan;
	private String programType;
}
