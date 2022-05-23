package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.enums.CashbackStatus;
import com.wallet.zokudo.enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class BrandOfferRequestDTO {
    private String branId;
    private String merchantId;
    private String programId;
    private List<Long> terminalId;
    /**
     * Update merchant cashback
     */
    private long id;
    //private Status status;
    // merchant offer filters
    private String page;
    private String size;
    private String dateRange;
    private String brandId;

}
