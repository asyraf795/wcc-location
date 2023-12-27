package com.test.wcc.util.calculator;

import com.test.wcc.constant.ErrorEnum;
import com.test.wcc.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceCalculator {
    private static final double EARTH_RADIUS = 6371; // radius in kilometers

    public static BigDecimal calculateDistance(BigDecimal latitude, BigDecimal longitude, BigDecimal latitude2, BigDecimal longitude2) {
        if (latitude == null) {
            throw new BusinessException(ErrorEnum.FROM_LATITUDE_IS_NULL.name());
        }
        if (longitude == null) {
            throw new BusinessException(ErrorEnum.FROM_LONGITUDE_IS_NULL.name());
        }
        if (latitude2 == null) {
            throw new BusinessException(ErrorEnum.TO_LATITUDE_IS_NULL.name());
        }
        if (longitude2 == null) {
            throw new BusinessException(ErrorEnum.TO_LONGITUDE_IS_NULL.name());
        }
        double lon1Radians = Math.toRadians(longitude.doubleValue());
        double lon2Radians = Math.toRadians(longitude2.doubleValue());
        double lat1Radians = Math.toRadians(latitude.doubleValue());
        double lat2Radians = Math.toRadians(latitude2.doubleValue());
        double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return BigDecimal.valueOf((EARTH_RADIUS * c));
    }

    private static double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(double x) {
        return x * x;
    }
}
