package com.wallet.zokudo.dto.request;

import lombok.Data;

@Data
public class BankTransactionDTO {

    private String dateRange;
    private String page;
    private String size;
}
