package com.test.wcc.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpsertLocationResponse {
    private String postcode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
