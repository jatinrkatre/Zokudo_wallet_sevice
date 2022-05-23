package com.wallet.zokudo.dto.request;

import lombok.Data;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

@Data
public class AccountPoolBalanceDTO {
    String agentHashId;
    String distributorHashId;
    String superDistributorHashId;
    MultiValueMap<String, String> agentIds;
    ArrayList<String> clientIdList;


}
