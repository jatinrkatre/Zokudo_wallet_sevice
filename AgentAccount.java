package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.PoolAccountType;
import com.wallet.zokudo.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "agent_account")
public class AgentAccount extends AbstractEntity {

    private static final long serialVersionUID = 8640291169699973757L;

    @Column(name = "agent_id")
    private long agentId;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private PoolAccountType accountType;

    @Column(name = "balance", precision = 19, scale = 4)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}