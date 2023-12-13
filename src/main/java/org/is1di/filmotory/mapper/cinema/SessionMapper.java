package org.is1di.filmotory.mapper.cinema;

import org.is1di.filmotory.model.entity.cinema.Session;
import org.is1di.filmotory.model.entity.cinema.SessionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    @Mapping(target = "filmId", source = "film.id")
    @Mapping(target = "cinemaId", source = "cinema.id")
    SessionDto.Output toOutput(Session entity);

    @Mapping(target = "film.id", source = "filmId")
    @Mapping(target = "cinema.id", source = "cinemaId")
    Session toEntity(SessionDto.Create dto);
}