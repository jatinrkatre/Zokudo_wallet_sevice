package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class FeeSetUpDTO {

    private String feeName;

    private String feeLevel;

    private String frequency;

    private double value;

    private boolean isFixed;

    private String clientCode;

    private long clientId;

    private long programId;

    private double cgstValue;

    private double sgstValue;
}
