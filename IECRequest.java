package com.wallet.zokudo.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IECRequest {

    List<IECTransactionRequest> transactionRequests = new ArrayList<>();

    private boolean isValid;

}
