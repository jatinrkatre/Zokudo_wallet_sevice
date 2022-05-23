package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 8983277735166005832L;

    @NotNull(message = "tcc can't be null")
    @NotEmpty(message = "tcc can't be empty")
    private String tcc;

    @NotNull(message = "entry can't be null")
    @NotEmpty(message = "entry can't be empty")
    private String entry;

    @NotNull(message = "otp can't be null")
    @NotEmpty(message = "otp can't be empty")
    private String otp;

    @NotNull(message = "cavv can't be null")
    @NotEmpty(message = "cavv can't be empty")
    private String cavv;

    @NotNull(message = "cvv1 can't be null")
    @NotEmpty(message = "cvv1 can't be empty")
    private String cvv1;

    @NotNull(message = "cvv2 can't be null")
    @NotEmpty(message = "cvv2 can't be empty")
    private String cvv2;

    @NotNull(message = "pin can't be null")
    @NotEmpty(message = "pin can't be empty")
    private String pin;

    @NotNull(message = "emv can't be null")
    @NotEmpty(message = "emv can't be empty")
    private String emv;
}
