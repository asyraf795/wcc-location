package com.test.wcc.controller;

import com.test.wcc.controller.request.CalculateDistanceRequest;
import com.test.wcc.controller.request.UpsertLocationRequest;
import com.test.wcc.controller.response.CalculateDistanceResponse;
import com.test.wcc.controller.response.UpsertLocationResponse;
import com.test.wcc.mapper.LocationMapper;
import com.test.wcc.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/calculate/distance")
    @ResponseBody
    public ResponseEntity<CalculateDistanceResponse> calculateDistanceBetweenTwoPostcode(@RequestBody @Valid CalculateDistanceRequest request) {
        log.info("Distance between two postcode request. request={} from={} to={}", request, request.getFrom(), request.getTo());
        return ResponseEntity.ok(LocationMapper.INSTANCE.map(locationService.calculateDistanceBetweenTwoDistance(request)));
    }

    @PostMapping("/location")
    @ResponseBody
    public ResponseEntity<UpsertLocationResponse> upsertLocation(@RequestBody @Valid UpsertLocationRequest request) {
        return ResponseEntity.ok(LocationMapper.INSTANCE.map(locationService.upsertLocation(request)));
    }
}
