package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.zokudo.entities.*;
import com.wallet.zokudo.enums.FeeType;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.enums.TransactionTypes;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FeeDeductionDto {

    private FeeType feeType;

    private String programId;

    private String clientId;

    private String customerHashId;

    private double amount;
    
    private String agentHashId;

    private String distributorHashId;

    private ProgramPlans programPlans;

    private long walletId;

    @JsonIgnore
    private ClientAccounts creditAccount;

    @JsonIgnore
    private ClientAccounts debitAccount;

    @JsonIgnore
    private AgentAccount agentCreditAccount;

    @JsonIgnore
    private AgentAccount agentDebitAccount;

    @JsonIgnore
    private String systemReferenceNumber;

//    @JsonIgnore
    private String proxyCardNumber;

    private Currency currency;

    @JsonIgnore
    private double fee;

    @JsonIgnore
    private double gstFee;

    private TransactionTypes transactionTypes;

    @JsonIgnore
    private Wallet wallet;

    @JsonIgnore
    private PocketDefinition pocketDefinition;

}
