package com.wallet.zokudo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FeeUpdateRequestDTO {

    @ApiModelProperty(example = "CONTACTLESS_FEES", required = true)
    @NotNull(message = "feeName cannot be null")
    private String feeName;

    @ApiModelProperty(example = "2.0", required = true)
    @NotNull(message = "feeValue cannot be null")
    private double feeValue;

    @ApiModelProperty(example = "2.0", required = true)
    @NotNull(message = "cgstValue cannot be null")
    private double cgstValue;

    @ApiModelProperty(example = "2.0", required = true)
    @NotNull(message = "sgstValue cannot be null")
    private double sgstValue;

    @ApiModelProperty(example = "SGD", required = true)
    @NotNull(message = "currency cannot be null")
    private String currency;

    @JsonProperty
    private boolean percent;

    private List<CollectiveValuesDTO> collectiveValues;

}
