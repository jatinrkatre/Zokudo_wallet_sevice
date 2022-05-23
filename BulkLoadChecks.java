package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.zokudo.entities.AgentAccount;
import com.wallet.zokudo.entities.ClientAccounts;
import com.wallet.zokudo.entities.Currency;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Data
public class BulkLoadChecks {

    /*@ApiModelProperty(required = true, example = "10.25")
    @Min(value = 1, message = "amount should be greater than zero")*/
    private double totalAmount;
//    private String totalAmount;
    private String totalCustomerCount;
    private String currencyCode;
    private String programId;
//    private String customerLoadAmount;
    /*@ApiModelProperty(required = true, example = "10.25")
    @Min(value = 1, message = "amount should be greater than zero")*/
    private double customerLoadAmount;

    @JsonIgnore
    private ClientAccounts debitAccount;

    @JsonIgnore
    private AgentAccount debitAgentAccount;

    @JsonIgnore
    private double loadFee;

    @JsonIgnore
    private Currency currency;
}
