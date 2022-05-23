package com.wallet.zokudo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClientPrefundUpdateRequest {

    @NotNull(message = "system reference number cannot be null")
    @NotEmpty(message = "system reference number cannot be empty")
    private String systemReferenceNumber;

    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be empty")
    private String status;

    private String prefundType;
    private long prefundReqId;

}
