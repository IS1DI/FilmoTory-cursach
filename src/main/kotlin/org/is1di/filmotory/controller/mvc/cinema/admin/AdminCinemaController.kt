package org.is1di.filmotory.controller.mvc.cinema.admin

import org.is1di.filmotory.mapper.cinema.CinemaMapper
import org.is1di.filmotory.model.entity.cinema.CinemaDto
import org.is1di.filmotory.service.CinemaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminCinemaController")
@RequestMapping("cinema")
class AdminCinemaController(
    private val cinemaService: CinemaService,
    private val cinemaMapper: CinemaMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "cinemaNew".also { model.addAttribute("cinema", CinemaDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute("cinema") cinema: CinemaDto.Create): String {
        val id = cinemaService.create(cinemaMapper.toEntity(cinema)).id
        return "redirect:/cinema/${id}"
    }
}