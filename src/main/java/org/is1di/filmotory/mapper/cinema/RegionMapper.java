package org.is1di.filmotory.mapper.cinema;

import org.is1di.filmotory.model.entity.cinema.Region;
import org.is1di.filmotory.model.entity.cinema.RegionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDto.Output toOutput(Region entity);

    Region toEntity(RegionDto.Create dto);
}