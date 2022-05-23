package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.enums.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PointsCashBackConfigDTO {

    private Long id;
    private List<String> programIdList;
    private String conversionType;
    private String conversionRate;
    private String dateRange;
    private String page;
    private String size;
    private String programName;
    private String programId;
    private Status status;
}
