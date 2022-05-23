package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.ChannelStatus;
import com.wallet.zokudo.enums.ChannelType;
import com.wallet.zokudo.enums.ProgramPlans;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "channel_limits", indexes = {
        @Index(name = "status", columnList = "status"),
        @Index(name = "customer_hash_id", columnList = "customer_hash_id"),
        @Index(name = "mobile", columnList = "mobile"),
        @Index(name = "customer_id", columnList = "customer_id"),
        @Index(name = "program_plan", columnList = "program_plan"),
        @Index(name = "channel_type_idx", columnList = "channel_type")
})
public class ChannelLimits extends AbstractEntity {

    @Column(name = "channel_limit", precision = 19, scale = 4)
    private double channelLimit;

    @Column(name = "remaining_limit", precision = 19, scale = 4)
    private double remainingLimit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "channel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column(name = "customer_hash_id", nullable = false)
    private String customerHashId;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "is_preference_set")
    private boolean isPreferenceSet = false;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "program_plan", nullable = true)
    @Enumerated(EnumType.STRING)
    private ProgramPlans programPlan;

}
