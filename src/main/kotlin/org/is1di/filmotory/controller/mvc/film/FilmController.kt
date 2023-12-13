package org.is1di.filmotory.controller.mvc.film

import org.is1di.filmotory.mapper.film.FilmMapper
import org.is1di.filmotory.service.FilmService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller("mvcFilmController")
@RequestMapping("film")
class FilmController(
    private val filmService: FilmService,
    private val filmMapper: FilmMapper,
) {
    @GetMapping
    fun page(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int,
        model: Model
    ): String =
        "filmPage".also { model.addAttribute("film", filmService.page(page, limit).map { filmMapper.toOutput(it) }) }

    @GetMapping("{id}")
    fun film(@PathVariable id: UUID, model: Model): String =
        "filmView".also { model.addAttribute("film", filmMapper.toOutput(filmService.getById(id))) }
}