package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.enums.Status;
import lombok.Data;

@Data
public class MerchantAuthValidatorRequestDTO {

    private long id;

    private String merchantId;

    private String terminalId;

    private Status status;
}
