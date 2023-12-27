package com.test.wcc.controller.request;

import com.test.wcc.annotation.ValidDecimalPoint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpsertLocationRequest {
    @NotBlank(message = "postcode.is.empty")
    private String postcode;
    @ValidDecimalPoint(message = "latitude.decimal.is.long")
    @NotNull(message = "latitude.is.null")
    private BigDecimal latitude;
    @ValidDecimalPoint(message = "longitude.decimal.is.long")
    @NotNull(message = "longitude.is.null")
    private BigDecimal longitude;
}
