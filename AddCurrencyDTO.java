package com.wallet.zokudo.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddCurrencyDTO {

    @Min(value = 1, message = "ClientId should be greater then 0")
    private long clientId;

    @Min(value = 1, message = "ProgramId should be greater then 0")
    private long programId;

    @NotNull(message = "Currency code can't be null")
    @NotEmpty(message = "Currency code can't be empty")
    private String currencyCode;
}
