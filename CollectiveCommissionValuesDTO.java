package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class CollectiveCommissionValuesDTO {

    private boolean flatValue;
    private boolean percentageValue;
    private String transactionType;
    private double commValue;
    private boolean isFixed;
}
