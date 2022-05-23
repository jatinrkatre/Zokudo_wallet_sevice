package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class NDCCycleDTO {

    private String startTime;
    private String endTime;
    private double transactionAmt;
    private long cycleId;

}
