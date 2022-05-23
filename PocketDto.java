package com.wallet.zokudo.dto.request;

import java.util.List;

import com.wallet.zokudo.entities.MccData;

import lombok.Data;

@Data
public class PocketDto {
 
	private List<MccData> mccDatas;
	private String clientCode;
	private String mccGroupName;
	private String pocketName;
	private String mccLimit;
	private String programId;
	private String maxLimit;
}
