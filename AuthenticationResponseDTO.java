package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    private boolean isValid;
    private String message;
    private long walletId;
    private long clientId;
    private long customerId;
    private double markup;
    private String clientName;
    private String clientEmailId;
    private String clientMobileNo;

}
