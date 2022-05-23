package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.TransactionStatus;
import com.wallet.zokudo.enums.TransactionTypes;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bank_transaction", indexes = {
        @Index(name = "client_id", columnList = "client_id"),
        @Index(name = "transaction_id", columnList = "transaction_id"),
        @Index(name = "transaction_ref_no", columnList = "transaction_ref_no", unique = true),
        @Index(name = "load_transaction_status_idx", columnList = "load_transaction_status"),
        @Index(name = "transaction_type_idx", columnList = "transaction_type"),
        @Index(name = "agent_id", columnList = "agent_id")
})
public class BankTransaction extends AbstractEntity {

    private static final long serialVersionUID = -1059502202622543425L;

    @Getter
    @Setter
    @Column(name = "client_id")
    private long clientId;

    @Getter
    @Setter
    @Column(name = "agent_id")
    private long agentId;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @Getter
    @Setter
    @Column(name = "amount", precision = 19, scale = 4)
    private double amount;

    @Getter
    @Setter
    @Column(name = "virtual_account_number")
    private String virtualAccountNumber;

    @Getter
    @Setter
    @Column(name = "ifsc")
    private String ifsc;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "transaction_ref_no", nullable = false)
    private String transactionRefNo;

    @Getter
    @Setter
    @Column(name = "retrieval_ref_no")
    private String retrievalRefNo;

    @Getter
    @Setter
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionTypes transactionType = TransactionTypes.Single_Load;

    @Getter
    @Setter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NA;

    @Getter
    @Setter
    @Column(name = "load_transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus loadTransactionStatus = TransactionStatus.NA;

    @Getter
    @Setter
    @Column(name = "response_code")
    private String responseCode;

    @Getter
    @Setter
    @Column(name = "response_message")
    private String responseMessage;

    @Getter
    @Setter
    @Column(name = "utr")
    private String utr;

    @Getter
    @Setter
    @Column(name = "original_transaction_ref_no")
    private String originalTransactionRefNo;

    @Getter
    @Setter
    @Column(name = "transaction_mode")
    private String transactionMode;
}
