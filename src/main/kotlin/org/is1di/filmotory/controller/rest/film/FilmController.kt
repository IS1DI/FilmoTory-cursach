package org.is1di.filmotory.controller.rest.film

import org.is1di.filmotory.mapper.film.FilmMapper
import org.is1di.filmotory.mapper.film.GenreMapper
import org.is1di.filmotory.model.entity.film.FilmDto
import org.is1di.filmotory.model.entity.film.GenreDto
import org.is1di.filmotory.service.FilmService
import org.is1di.filmotory.service.GenreService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/film")
class FilmController(
    private val filmService: FilmService,
    private val filmMapper: FilmMapper,
) {
    @GetMapping("all")
    fun getAll(): List<FilmDto.Output> =
        filmService.getAll().map { filmMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody film: FilmDto.Create): FilmDto.Output =
        filmMapper.toOutput(filmService.create(filmMapper.toEntity(film)))

    @GetMapping
    fun page(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int
    ): Page<FilmDto.Output> =
        filmService.page(page, limit).map { filmMapper.toOutput(it) }

    @GetMapping("{id}")
    fun getById(@PathVariable id: UUID): FilmDto.Output =
        filmMapper.toOutput(filmService.getById(id))
}


@RestController
@RequestMapping("api/genre")
class GenreController(
    private val genreService: GenreService,
    private val genreMapper: GenreMapper,
) {
    @GetMapping("all")
    fun getAll(): List<GenreDto.Output> =
        genreService.list().map { genreMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody genre: GenreDto.Create): GenreDto.Output =
        genreMapper.toOutput(genreService.create(genreMapper.toEntity(genre)))
}