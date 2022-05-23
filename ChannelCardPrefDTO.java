package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class ChannelCardPrefDTO {

    private String channelType;
    private String cardHashId;
    private String proxyCardNumber;
    private String status;
    private double amount;
}
