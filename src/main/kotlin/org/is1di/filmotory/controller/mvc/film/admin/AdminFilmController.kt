package org.is1di.filmotory.controller.mvc.film.admin

import org.is1di.filmotory.mapper.film.FilmMapper
import org.is1di.filmotory.model.entity.film.FilmDto
import org.is1di.filmotory.service.FilmService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminFilmController")
@RequestMapping("film")
class AdminFilmController(
    private val filmService: FilmService,
    private val filmMapper: FilmMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "film".also { model.addAttribute("film", FilmDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute film: FilmDto.Create): String {
        val id = filmService.create(filmMapper.toEntity(film)).id
        return "redirect:/film/$id"
    }
}