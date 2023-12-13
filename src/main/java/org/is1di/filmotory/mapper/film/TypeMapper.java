package org.is1di.filmotory.mapper.film;

import org.is1di.filmotory.model.entity.film.Type;
import org.is1di.filmotory.model.entity.film.TypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeDto.Output toOutput(Type entity);

    Type toEntity(TypeDto.Create dto);
}