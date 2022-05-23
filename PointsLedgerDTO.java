package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.entities.PocketDefinition;
import com.wallet.zokudo.entities.Wallet;
import com.wallet.zokudo.enums.PointsType;
import com.wallet.zokudo.enums.TransactionStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class PointsLedgerDTO {

    private double transactionAmount;

    private String customerHashId;

    private String cardHashId;

    private  String cardNumber;

    private String proxyNumber;

    private double cashbackPoint;

    private String channel;

    private long clientId;

    private long programId;

    private String merchantCategoryCode;

    private String merchantId;

    private String merchantName;

    private String network;

    private PointsType pointsType;

    private PocketDefinition pocketDefinition;

    private Wallet wallet;

    private String cardType;

    private String brandId;

    private double points;

    private String transactionRefNo;

    private double currentBalance;

    private String orgRRN;

    /* Filters fields for cashback point ledger */
    private String dateRange;
    private String page;
    private String size;
    private String mobileNo;
    private Date startDate;
    private Date endDate;

    private TransactionStatus status;

    private String comments;

}
