package org.is1di.filmotory.controller.rest.cinema

import org.is1di.filmotory.mapper.cinema.CinemaMapper
import org.is1di.filmotory.model.entity.cinema.CinemaDto
import org.is1di.filmotory.service.CinemaService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/cinema")
class CinemaController(
    private val cinemaService: CinemaService,
    private val cinemaMapper: CinemaMapper,
) {
    @PostMapping
    fun create(@RequestBody cinema: CinemaDto.Create): CinemaDto.Output =
        cinemaMapper.toOutput(cinemaService.create(cinemaMapper.toEntity(cinema)))

    @GetMapping
    fun page(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int
    ): Page<CinemaDto.Output> =
        cinemaService.page(page, limit).map { cinemaMapper.toOutput(it) }

    @GetMapping("{id}")
    fun getByTitle(@PathVariable id: UUID): CinemaDto.Output =
        cinemaMapper.toOutput(cinemaService.getById(id))
}