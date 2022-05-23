package com.wallet.zokudo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class NDCLimitDTO {

    private List<NDCCycleDTO> cycles;
    private long retailerId;
    private String retailerName;
    private NDCCycleDTO cycle;
    private String retailerHashId;
}
