package com.wallet.zokudo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "agent_virtual_accounts", indexes = {
        @Index(name = "agent_id", columnList = "agent_id"),
        @Index(name = "virtual_account", columnList = "virtual_account"),
        @Index(name = "email", columnList = "email")
})
public class AgentVirtualAccounts extends AbstractEntity {

    private static final long serialVersionUID = 8640291169699973757L;

    @Column(name = "agent_id")
    private long agentId;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "virtual_account", nullable = false, unique = true)
    private String virtualAccount;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "ifsc")
    private String ifsc;

    @Column(name = "email")
    private String email;
}
