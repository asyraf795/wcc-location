package com.test.wcc.factory;

import com.test.wcc.controller.request.UpsertLocationRequest;
import com.test.wcc.model.Location;

import java.math.BigDecimal;

public class LocationFactory {

    public static Location create(UpsertLocationRequest request) {
        return create(request.getPostcode(), request.getLatitude(), request.getLongitude());
    }

    public static Location create(String postcode, BigDecimal latitude, BigDecimal longitude) {
        return Location.builder()
                .postcode(postcode)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
