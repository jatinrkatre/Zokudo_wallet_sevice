package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class CollectiveValuesDTO {

    private boolean flatValue;
    private boolean percentageValue;
    private double feeValue;
    private String feeName;
    private String feeCurrency;
    private double cgstValue;
    private double sgstValue;
}
