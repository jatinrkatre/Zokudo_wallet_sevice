package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class CollectiveRevenueValuesDTO {
    private boolean flatValue;
    private boolean percentageValue;
    private String transactionType;
    private double revenueValue;
    private boolean isFixed;
}
