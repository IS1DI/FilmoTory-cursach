package org.is1di.filmotory.controller.mvc.cinema

import org.is1di.filmotory.mapper.cinema.CinemaMapper
import org.is1di.filmotory.service.CinemaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller("mvcCinemaController")
@RequestMapping("cinema")
class CinemaController(
    private val cinemaService: CinemaService,
    private val cinemaMapper: CinemaMapper
) {
    @GetMapping
    fun cinemaPage(
        model: Model,
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int
    ): String =
        "cinemaPage".also { model.addAttribute("cinemas", cinemaService.page(page, limit)) }

    @GetMapping("{id}")
    fun cinema(@PathVariable id: UUID, model: Model): String =
        "cinemaView".also { model.addAttribute("cinema", cinemaMapper.toOutput(cinemaService.getById(id))) }
}