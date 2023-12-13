package org.is1di.filmotory.mapper.cinema;

import org.is1di.filmotory.model.entity.cinema.Category;
import org.is1di.filmotory.model.entity.cinema.CategoryDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto.Output toOutput(Category entity);

    Category toEntity(CategoryDto.Create dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category toUpdate(CategoryDto.Update dto, @MappingTarget Category entity);
}