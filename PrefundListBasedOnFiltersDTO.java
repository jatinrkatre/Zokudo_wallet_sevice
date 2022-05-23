package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class PrefundListBasedOnFiltersDTO {
    private String page;
    private String size;
    private String dateRange;
    private  Long clientId;
    private  Long agentId;
    private  Long distributorId;
    private  String status;
    private  String referenceNumber;
    private String agentName;
    private String clientName;
    private String distributorName;
    private String bankReferenceNumber;
    private String clientHash;
}
