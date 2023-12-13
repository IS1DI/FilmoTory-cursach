package org.is1di.filmotory.mapper.film;

import org.is1di.filmotory.model.entity.cinema.Session;
import org.is1di.filmotory.model.entity.film.Film;
import org.is1di.filmotory.model.entity.film.FilmDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    @Mapping(target = "isEnabled", source = "enabled")
    FilmDto.Output toOutput(Film entity);

    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "country.country", source = "country")
    @Mapping(target = "type.type", source = "type")
    @Mapping(target = "enabled", expression = "java(true)")
    Film toEntity(FilmDto.Create dto);

    default List<UUID> mapFilms(List<Film> films) {
        return films.stream().map(Film::getId).collect(Collectors.toList());
    }

    default List<UUID> mapSessions(List<Session> sessions) {
        return sessions.stream().map(Session::getId).collect(Collectors.toList());
    }
}