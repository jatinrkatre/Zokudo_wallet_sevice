package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.entities.MccData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MCCDataDTO {

    private List<MccData> mccDatas;

    private String mccCodes;

    private boolean isDefault;

    private String name;

    @NotNull(message = "ProgramId can't be null")
    private long programId;

    @NotNull(message = "ClientId can't be null")
    private long clientId;
}
