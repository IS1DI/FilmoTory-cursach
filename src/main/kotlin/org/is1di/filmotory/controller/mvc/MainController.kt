package org.is1di.filmotory.controller.mvc

import org.is1di.filmotory.service.CinemaService
import org.is1di.filmotory.service.FilmService
import org.is1di.filmotory.service.SessionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("main")
class MainController(
    private val filmService: FilmService,
    private val cinemaService: CinemaService,
    private val sessionService: SessionService,
) {
    @GetMapping
    fun mainPage(model: Model): String =
        "main".also {
            model.addAttribute("top10films", filmService.top(1, 10))
            model.addAttribute("top10cinemas", cinemaService.top(1, 10))
            model.addAttribute("sessionsUpcoming", sessionService.upcoming(1, 10))
        }

}