package com.wallet.zokudo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReconRequestDTO {
    private String size;
    private String page;
    private String transactionDate;
    private Long hostTransactionCount=0L;
    private Long processorTransactionCount=0L;
    private Long nrnsCount=0L;
    private Long rnsCount=0L;
    private Long rsCount=0L;
    private MultipartFile reconFile;
    private String reconFileName;
    private String reconProcessCode;
    private String uploadFileUrl;
    private Long fpRsCount=0L;
    private Long stRsCount=0L;
    private String fileExtension;
}
