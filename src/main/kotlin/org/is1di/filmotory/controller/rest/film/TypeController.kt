package org.is1di.filmotory.controller.rest.film

import org.is1di.filmotory.mapper.film.TypeMapper
import org.is1di.filmotory.model.entity.film.TypeDto
import org.is1di.filmotory.service.TypeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/type")
class TypeController(
    private val typeService: TypeService,
    private val typeMapper: TypeMapper,
) {
    @GetMapping("all")
    fun getAll(): List<TypeDto.Output> =
        typeService.list().map { typeMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody genre: TypeDto.Create): TypeDto.Output =
        typeMapper.toOutput(typeService.create(typeMapper.toEntity(genre)))
}