package com.test.wcc.mapper;

import com.test.wcc.constant.Constant;
import com.test.wcc.dto.LocationDTO;
import com.test.wcc.controller.response.CalculateDistanceResponse;
import com.test.wcc.controller.response.UpsertLocationResponse;
import com.test.wcc.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = Constant.class)
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target="unit", expression = "java(Constant.DISTANCE_UNIT_KILOMETER)")
    @Mapping(target="from", source = "from")
    @Mapping(target="to", source = "to")
    @Mapping(target="distance", source = "distance")
    CalculateDistanceResponse map(LocationDTO locationDTO);

    UpsertLocationResponse map(Location location);
}
