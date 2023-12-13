package org.is1di.filmotory.mapper.film;

import org.is1di.filmotory.model.entity.film.Country;
import org.is1di.filmotory.model.entity.film.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto.Output toOutput(Country entity);

    Country toEntity(CountryDto.Create dto);
}