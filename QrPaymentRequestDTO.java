package com.wallet.zokudo.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class QrPaymentRequestDTO {

    @ApiParam(required = true, example = "00020101021102164000010000000050061660000100000000500825KVBL00012071207500010012826350010A0000005240110TEST12@vpa02031.027240010A00000052401066005635204549953033565802IN5904Test6007CHENNAI610660006162130709600563001630429C2")
    @NotNull(message = "merchantData cannot be null")
    @NotEmpty(message = "merchantData cannot be empty")
    @Lob
    private String merchantData;

    @ApiParam(required = true, example = "sanjeevi")
    @NotNull(message = "fromEntityId cannot be null")
    @NotEmpty(message = "fromEntityId cannot be empty")
    private String fromEntityId;

    private String toEntityId;

    @ApiParam(required = true, example = "Test")
    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @ApiParam(required = true, example = "120.40")
//    @Min(value = 1, message = "amount should be greater than zero")
    private String amount;

    private String externalTransactionId;

    @ApiParam(required = true, example = "Yash")
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;

    private String lastName;

    @ApiParam(required = true, example = "9702637632")
    @NotNull(message = "contactNo cannot be null")
    @NotEmpty(message = "contactNo cannot be empty")
    private String contactNo;

    @ApiParam(required = true, example = "5550041100000001")
    @NotNull(message = "senderCardNo cannot be null")
    @NotEmpty(message = "senderCardNo cannot be empty")
    private String senderCardNo;

    @ApiParam(required = true, example = "4321_2021")
    @NotNull(message = "billRefNo cannot be null")
    @NotEmpty(message = "billRefNo cannot be empty")
    private String billRefNo;

    private String additionalData;
    private String merchantId;
    private String terminalId;
    private String mcc;
    private String merchantName;
    private String proxyCardNo;
}
