package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.zokudo.entities.ChannelLimits;
import com.wallet.zokudo.enums.ChannelStatus;
import com.wallet.zokudo.enums.ChannelType;
import lombok.Data;

@Data
public class ChannelLimitDTO {

    private String customerHashId;

    private String channelLimit;

    private ChannelType channelType;

    private ChannelStatus status;

    @JsonIgnore
    private ChannelLimits channelLimits;

    private String cardHashId;

    private String proxyCardNo;
}
