package com.wallet.zokudo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DashboardRequestDTO {
    @NotNull(message="type must not be null")
    @NotEmpty(message="type must not be empty")
    private String type;
    private List<Integer> clientId;
    private List<Integer> distributorId;
    private List<String> distributorHashId;
    private List<String> agentId;
    private String openingBalanceDate;
    private String closingBalanceDate;
    private String loadDateRange;
    private String transactionDateRange;
    private String cashbackDateRange;
    private String refundDateRange;
    private String prefundBalanceDate;
}
