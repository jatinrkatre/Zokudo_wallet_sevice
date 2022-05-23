package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.ChannelStatus;
import com.wallet.zokudo.enums.ChannelType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="channel_card_preference")
public class ChannelCardPreference extends AbstractEntity{

    private String cardHashId;

    private String proxyCardNo;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    private ChannelType channelType;

    private String mobileNumber;

}
