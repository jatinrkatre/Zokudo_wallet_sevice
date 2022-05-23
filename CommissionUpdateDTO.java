package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommissionUpdateDTO {

    @ApiModelProperty(example = "DEBIT", required = true)
    @NotNull(message = "transaction type cannot be null")
    private String transactionType;

    @ApiModelProperty(example = "2.0", required = true)
    @NotNull(message = "comm value cannot be null")
    private double commValue;

    @JsonProperty
    private boolean percent;

    private List<CollectiveCommissionValuesDTO> collectiveValues;
}
