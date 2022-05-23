package com.wallet.zokudo.entities;

import com.wallet.zokudo.enums.PointsType;
import com.wallet.zokudo.enums.Status;
import com.wallet.zokudo.enums.TransactionStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@Table(name="cashback_points_ledger")
public class CashBackPointsLedger extends  AbstractEntity{

    @Column(name="transaction_amount")
    private double transactionAmount;

    @Column(name="customer_hash_id")
    private String customerHashId;

    @Column(name="card_hash_id")
    private String cardHashId;

    @Column(name="card_number")
    private  String cardNumber;

    @Column(name="proxy_number")
    private String proxyNumber;

    @Column(name="cashback_points")
    private double cashbackPoint;

    @Column(name="channel")
    private String channel;

    @Column(name="client_id")
    private long clientId;

    @Column(name="program_id")
    private long programId;

    @Column(name = "merchant_category_code")
    private String merchantCategoryCode;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "network")
    private String network;

    @Column(name="points_type")
    @Enumerated(EnumType.STRING)
    private PointsType pointsType;

    @ManyToOne
    @JoinColumn(name = "pocket_definition_id", referencedColumnName = "id")
    private PocketDefinition pocketDefinition;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name="current_balance")
    @ColumnDefault("0")
    private Double currentBalance;

    @Column(name="transaction_ref_no")
    private String transactionRefNo;

    @Column(name="org_rrn")
    private String originalRrn;

    @Column(name="comment")
    private String comment;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
