package com.wallet.zokudo.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClientPrefundRequestDTO {

    @ApiParam(required = true, example = "BR123456789")
    @NotNull(message = "BankReferenceNumber cannot be null")
    @NotEmpty(message = "BankReferenceNumber cannot be empty")
    private String bankReferenceNumber;

    @ApiParam(required = true, example = "120.40")
    @Min(value = 1, message = "amount should be greater than zero")
    private double amount;

    private String comments;

    private MultipartFile file;

    private String requester_id;

    @ApiParam(required = true, example = "2020-02-02")
    @NotNull(message = "DateOfTransfer cannot be null")
    @NotEmpty(message = "DateOfTransfer cannot be empty")
    private String dateOfTransfer;

    @ApiParam(required = true, example = "SGD")
    @NotNull(message = "currency code cannot be null")
    @NotEmpty(message = "currency code cannot be empty")
    private String currencyCode;

    @ApiParam(required = true, example = "123456789")
    @NotNull(message = "ClientAccountNumber cannot be null")
    @NotEmpty(message = "ClientAccountNumber cannot be empty")
    private String clientAccountNumber;

    @ApiParam(required = true, example = "987654321")
    @NotNull(message = "M2PAccountNumber cannot be null")
    @NotEmpty(message = "M2PAccountNumber cannot be empty")
    private String m2pAccountNumber;
    private long clientId;
    private long agentId;
}
