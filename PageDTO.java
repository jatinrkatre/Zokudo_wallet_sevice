package com.wallet.zokudo.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageDTO {

    @ApiModelProperty(notes = "Page size", example = "20", dataType = "integer")
    private int size = 20;
    @ApiModelProperty(notes = "Page number", example = "0", dataType = "integer")
    private int page = 0;
    @ApiModelProperty(notes = "Property order", example = "DESC", allowableValues = "DESC,ASC", dataType = "string")
    private String order = Sort.Direction.DESC.name();
    @ApiModelProperty(notes = "Property name", example = "createdAt", allowableValues = "createdAt", dataType = "string")
    private String property = "createdAt";

    private String startDate;

    private String endDate;
}