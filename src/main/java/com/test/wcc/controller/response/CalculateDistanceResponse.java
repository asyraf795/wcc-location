package com.test.wcc.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.test.wcc.util.serializer.DistanceSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
public class CalculateDistanceResponse {
    private String unit;

    @JsonSerialize(using = DistanceSerializer.class)
    private BigDecimal distance;
    private Location from;
    private Location to;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    public static class Location {
        private String postcode;
        private BigDecimal latitude;
        private BigDecimal longitude;
    }
}
