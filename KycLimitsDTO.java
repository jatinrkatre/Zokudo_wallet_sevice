package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class KycLimitsDTO {

    private String balanceLimit;

//    private String loadLimit;

    private String monthlyLoadLimit;

    private String annualLoadLimit;

    private String accountType;

    private String customerId;
}
