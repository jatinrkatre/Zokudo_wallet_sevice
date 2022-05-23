package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class IECTransactionRequest {

    private String requestId;

    private String challanCode;

    private String challanNo;

    private String clientAccountNO;

    private String clientName;

    private String clientCompanyName;

    private String amount;

    private String remitterName;

    private String remetterAccountNo;

    private String remetterIfsc;

    private String remetterBank;

    private String remetterBranch;

    private String remetterUtr;

    private String payMethod;

    private String creditAccountNo;

    private String inwardRefNum;

    private String creditTime;

    private Long agentId;

    private String agentName;

    private Long distributorId;

    private String distributorName;

    private String requesterName;
}
