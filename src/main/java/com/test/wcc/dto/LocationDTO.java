package com.test.wcc.dto;

import com.test.wcc.model.Location;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder(toBuilder = true)
public class LocationDTO {
    private Location from;
    private Location to;
    private BigDecimal distance;
}
