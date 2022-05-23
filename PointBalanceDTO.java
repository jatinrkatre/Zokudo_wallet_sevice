package com.wallet.zokudo.dto.request;

import com.wallet.zokudo.entities.*;
import lombok.Data;

import java.util.Date;

@Data
public class PointBalanceDTO {

    private String mobile;
    private double balance;
    private String firstName;
    private String lastName;
    private String transactionRefNo;
    private double redeemPoints;
    private String proxyCardNo;

    /**
     * Filters for Point Balance List
     */
    private String dateRange;
    private String page;
    private String size;
    private Date startDate;
    private Date endDate;

    private CardDetailsResponse cardDetailsResponse;
    private PocketDefinition pocketDefinition;
    private WalletBalance walletBalance;
    private Wallet wallet;
    private Currency currency;
    private double currentBalance;
    private String cashbackRefNo;
    private Transaction cashbackTransaction;
    private double currentPointBalance;
    private long pointsToRedeem;
    private long clientId;
}

