package org.is1di.filmotory.controller.rest.film

import org.is1di.filmotory.mapper.film.AuthorMapper
import org.is1di.filmotory.model.entity.film.AuthorDto
import org.is1di.filmotory.service.AuthorService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/author")
class AuthorController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper,
) {
    @PostMapping
    fun create(@RequestBody author: AuthorDto.Create): AuthorDto.Output =
        authorMapper.toOutput(authorService.create(authorMapper.toEntity(author)))

    @GetMapping
    fun page(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int
    ): Page<AuthorDto.Output> =
        authorService.page(page, limit).map { authorMapper.toOutput(it) }

    @GetMapping("{id}")
    fun getByTitle(@PathVariable id: UUID): AuthorDto.Output =
        authorMapper.toOutput(authorService.getById(id))
}