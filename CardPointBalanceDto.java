package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class CardPointBalanceDto {
    private String cardType;
    private String cardHashId;
    private String customerHashId;
    private String maskCardNumber;
    private String expiryDate;
    private long walletId;
    private long programId;
    private String programName;
    private long cardId;
    private long totalAccumulatedPoints;
    private long pointsSubmittedToRedeem;
    private long pointsEligibleToRedeem;
    private double balanceToRedeem;
    private String proxyCardNo;
}
