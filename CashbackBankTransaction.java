package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cashback_bank_transaction", indexes = {
        @Index(name = "client_id", columnList = "client_id"),
        @Index(name = "transaction_id", columnList = "transaction_id"),
        @Index(name = "transaction_ref_no", columnList = "transaction_ref_no", unique = true)
})
public class CashbackBankTransaction extends AbstractEntity {

    private static final long serialVersionUID = -1059502202622543425L;

    @Getter
    @Setter
    @Column(name = "client_id")
    private long clientId;

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
    @Column(name = "account_number")
    private String accountNumber;

    @Getter
    @Setter
    @Column(name = "proxy_card_number")
    private String proxyCardNumber;

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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NA;

    @Getter
    @Setter
    @Column(name = "cashback_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus cashbackStatus = TransactionStatus.Pending;

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
