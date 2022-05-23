package com.wallet.zokudo.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class P2PTransferDTO {

    @ApiModelProperty(required = true, example = "10")
    @Min(value = 1, message = "Amount should be greater than zero")
    private double amount;

    private String currencyCode;

    @ApiModelProperty(required = true)
    @NotNull(message = "Receiver wallet hash cannot be null")
    @NotEmpty(message = "Receiver wallet hash cannot be empty")
    private String destinationWalletHashId;
}
