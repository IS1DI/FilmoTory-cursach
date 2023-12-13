package org.is1di.filmotory.mapper.film;

import org.is1di.filmotory.model.entity.film.Author;
import org.is1di.filmotory.model.entity.film.AuthorDto;
import org.is1di.filmotory.model.entity.film.Film;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto.Output toOutput(Author entity);

    Author toEntity(AuthorDto.Create dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Author toUpdate(AuthorDto.Update dto, @MappingTarget Author entity);

    default List<UUID> map(List<Film> films) {
        return films.stream().map(Film::getId).collect(Collectors.toList());
    }
}