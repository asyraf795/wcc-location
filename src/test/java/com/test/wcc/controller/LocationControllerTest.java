package com.test.wcc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.wcc.controller.request.CalculateDistanceRequest;
import com.test.wcc.model.Location;
import com.test.wcc.repository.LocationRepository;
import com.test.wcc.service.LocationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LocationControllerTest {
    private AutoCloseable closeable;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new LocationController(locationService)).build();
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    private static final Location FROM = Location.builder().postcode("ZE2 9FX").id(1794534).latitude(BigDecimal.valueOf(60.0000380)).longitude(BigDecimal.valueOf(-1.2424940)).build();
    private static final Location TO = Location.builder().postcode("AB10 1XG").id(1).latitude(BigDecimal.valueOf(57.1441560)).longitude(BigDecimal.valueOf(-2.1148640)).build();
    private static final Location TO2 = Location.builder().postcode("IV51 0AE").id(930537).build();

    @Test
    void givenValidPostcodeNonNullLatitudeLongitude_whenCalculating_thenReturnDistanceResponse() throws Exception {
        CalculateDistanceRequest request = new CalculateDistanceRequest();
        request.setFrom(FROM.getPostcode());
        request.setTo(TO.getPostcode());

        Mockito.when(locationRepository.findByPostcode(FROM.getPostcode())).thenReturn(Optional.of(FROM));
        Mockito.when(locationRepository.findByPostcode(TO.getPostcode())).thenReturn(Optional.of(TO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/location/calculate/distance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request));

        String jsonString = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map map = objectMapper.readValue(jsonString, Map.class);
        Assertions.assertEquals(321.55, map.get("distance"));
    }

    @Test
    void givenValidPostcodesWithFromPostcodeLatitudeIsNull_whenCalculating_thenReturnFromLatitudeIsNullError() throws Exception {

    }

//    @Test
//    void givenInvalidFromPostcode_whenCalculating_thenReturnFromPostcodeNotExistError() throws Exception {

//    }
//
//    @Test
//    void givenNewPostcode_whenSaving_thenReturnSavedPostcodeResponse() throws Exception {

//    }
//
//    @Test
//    void givenOldPostcodeModified_whenSaving_thenReturnSavedPostcodeResponse() throws Exception {

//    }
//
//    @Test
//    void givenPostcodeWithLongDecimalLatitude_whenSaving_thenReturnLongDecimalLatitudeError() throws Exception {

//    }
}
