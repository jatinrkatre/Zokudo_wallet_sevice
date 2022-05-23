package com.wallet.zokudo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auh_transaction_logs", indexes = {
        @Index(name = "transaction_id", columnList = "transaction_id")
})
public class AuthTransactionLogs extends AbstractEntity {

    private static final long serialVersionUID = 8640291169699973757L;

    @Lob
    @Column(name = "request_log")
    private String requestLog;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_desc")
    private String responseDesc;
}