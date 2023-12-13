package org.is1di.filmotory.mapper.film;

import org.is1di.filmotory.model.entity.film.Film;
import org.is1di.filmotory.model.entity.film.Genre;
import org.is1di.filmotory.model.entity.film.GenreDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto.Output toOutput(Genre entity);

    Genre toEntity(GenreDto.Create dto);

    default List<UUID> map(List<Film> films) {
        return films.stream().map(Film::getId).collect(Collectors.toList());
    }
}