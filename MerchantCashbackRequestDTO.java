package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.enums.CashbackStatus;
import lombok.Data;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;

@Data
public class MerchantCashbackRequestDTO {

    private String brandId;

    private String merchantId;

    private List<String> merchantIdList;

    private String category;

    private List<String> programIdList;

    private List<String> terminalIdList;

    private HashMap<String, List<String>> terminalIdListMap;

    private boolean fixed;

    private String cashbackValue;

    private String cashbackPoint;

    private String brandName;

    private String merchantName;
    /**
     * Update merchant cashback
     */
    private long id;
    private CashbackStatus status;

    /**
     * Update brand offer
     */
    private String offerId;
    private String offerStartDate;
    private String offerEndDate;
    
    // merchant cashback filters
    private String page;
    private String size;
    private String dateRange;
    private List<String> programName;
    private String offerStatus;
}
