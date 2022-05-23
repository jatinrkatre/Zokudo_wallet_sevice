package com.wallet.zokudo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddWalletDTO {

    @NotNull(message = "CustomerId cannot be null")
    private long customerId;

    @NotNull(message = "ClientId cannot be empty")
    private long clientId;

    @NotNull(message = "ProgramId cannot be empty")
    private long programId;

    @NotNull(message = "CardType cannot be empty")
    private String cardType;

    @NotNull(message = "ProgramPlan cannot be empty")
    private String programPlan;

}
