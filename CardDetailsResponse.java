package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.enums.AuthType;
import lombok.Data;

@Data
public class CardDetailsResponse {
    private String message;
    private String status;
    private String cardStatus;
    private String cardActivationStatus;
    private String cardType;
    private String cardHashId;
    private String customerHashId;
    private String maskCardNumber;
    private long walletId;
    private long programId;
    private long cardId;
    private long clientId;
    private AuthType authType;
    private String agentHashId;
    private String distributorHashId;
    private String proxyCardNo;
}
