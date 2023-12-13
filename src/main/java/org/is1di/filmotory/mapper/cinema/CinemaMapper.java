package org.is1di.filmotory.mapper.cinema;

import org.is1di.filmotory.model.entity.cinema.Cinema;
import org.is1di.filmotory.model.entity.cinema.CinemaDto;
import org.is1di.filmotory.model.entity.cinema.Session;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    @Mapping(target = "region", source = "region.region")
    CinemaDto.Output toOutput(Cinema entity);

    @Mapping(target = "region.region", source = "region")
    @Mapping(target = "category.category", source = "category")
    Cinema toEntity(CinemaDto.Create dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cinema toUpdate(CinemaDto.Update dto, @MappingTarget Cinema entity);

    default List<UUID> mapSessions(List<Session> sessions) {
        return sessions.stream().map(Session::getId).collect(Collectors.toList());
    }
}