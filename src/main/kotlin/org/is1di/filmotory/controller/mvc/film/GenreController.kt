package org.is1di.filmotory.controller.mvc.film

import org.is1di.filmotory.mapper.film.GenreMapper
import org.is1di.filmotory.service.GenreService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcGenreController")
@RequestMapping("genre")
class GenreController(
    private val genreService: GenreService,
    private val genreMapper: GenreMapper,
) {
    @GetMapping
    fun list(model: Model): String =
        "genrePage".also { model.addAttribute("genres", genreService.list().map { genreMapper.toOutput(it) }) }

    @GetMapping("{genreTitle}")
    fun genre(@PathVariable genreTitle: String, model: Model): String =
        "genreView".also { model.addAttribute("genre", genreMapper.toOutput(genreService.get(genreTitle))) }
}