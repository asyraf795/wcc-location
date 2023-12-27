package com.test.wcc.service;

import com.test.wcc.dto.LocationDTO;
import com.test.wcc.factory.LocationFactory;
import com.test.wcc.controller.request.CalculateDistanceRequest;
import com.test.wcc.controller.request.UpsertLocationRequest;
import com.test.wcc.model.Location;
import com.test.wcc.repository.LocationRepository;
import com.test.wcc.util.calculator.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.test.wcc.constant.ErrorEnum.FROM_DOES_NOT_EXIST;
import static com.test.wcc.constant.ErrorEnum.TO_DOES_NOT_EXIST;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationDTO calculateDistanceBetweenTwoDistance(CalculateDistanceRequest request) {
        Location from = locationRepository.findByPostcode(request.getFrom()).orElseThrow(() -> new IllegalArgumentException(FROM_DOES_NOT_EXIST.name()));
        Location to = locationRepository.findByPostcode(request.getTo()).orElseThrow(() -> new IllegalArgumentException(TO_DOES_NOT_EXIST.name()));
        BigDecimal distance = DistanceCalculator.calculateDistance(from.getLatitude(), from.getLongitude(), to.getLatitude(), to.getLongitude());
        return LocationDTO.builder().from(from).to(to).distance(distance).build();
    }

    public Location upsertLocation(UpsertLocationRequest request) {
        Location newLocation = LocationFactory.create(request);
        Optional<Location> location = locationRepository.findByPostcode(request.getPostcode());
        if (location.isEmpty()) {
            return locationRepository.save(newLocation);
        }
        return location.get().equals(newLocation) ? location.get() : locationRepository.save(location.get().toBuilder().latitude(request.getLatitude()).longitude(request.getLongitude()).build());
    }
}
