package org.is1di.filmotory.controller.mvc.film.admin

import org.is1di.filmotory.mapper.film.GenreMapper
import org.is1di.filmotory.model.entity.film.GenreDto
import org.is1di.filmotory.service.GenreService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminGenreController")
@RequestMapping("genre")
class AdminGenreController(
    private val genreService: GenreService,
    private val genreMapper: GenreMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "genreNew".also { model.addAttribute("genre", GenreDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute genre: GenreDto.Create): String {
        val genreTitle = genreService.create(genreMapper.toEntity(genre))
        return "redirect:/genre/$genreTitle"
    }
}