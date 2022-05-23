package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class ReconReportBasedOnFiltersDTO {

    private String page;
    private String size;
    private String dateRange;
    private String reconType;
    private String reconStatus;
    private String fileName;
    private String uploadedFileName;
    private String transactionDate;
}
