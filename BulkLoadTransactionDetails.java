package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.TransactionTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bulk_load_transaction_details")
public class BulkLoadTransactionDetails extends AbstractEntity {

    private static final long serialVersionUID = -1059502202622543425L;

    @Getter
    @Setter
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionTypes transactionType;

    @ManyToOne
    @JoinColumn(name = "bank_transaction_id", referencedColumnName = "id")
    private BankTransaction bankTransaction;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    public void setBankTransaction(BankTransaction bankTransaction) { this.bankTransaction = bankTransaction; }

    public BankTransaction getBankTransaction() { return bankTransaction; }

    public void setTransaction(Transaction transaction) { this.transaction = transaction; }

    public Transaction getTransaction() { return transaction; }
}
