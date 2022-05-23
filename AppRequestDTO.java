package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class AppRequestDTO {

    private String mobileNumber;

    private String proxyCardNumber;

    private String startDate;

    private String endDate;

    private String mccCode;

    private long walletId;

    private long clientId;

    private long programId;

    private int pageNo;
    private int noOfRecords;
}
