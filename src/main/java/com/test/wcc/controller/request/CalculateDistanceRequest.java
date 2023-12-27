package com.test.wcc.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateDistanceRequest {
    @NotBlank(message = "from.postcode.is.empty")
    private String from;
    @NotBlank(message = "to.postcode.is.empty")
    private String to;
}
