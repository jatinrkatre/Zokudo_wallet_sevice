package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class AddWalletBalanceDTO {
    private double balance;
    private long currencyId;
    private long pocketDefinitionId;
    private String walletHashId;
}
