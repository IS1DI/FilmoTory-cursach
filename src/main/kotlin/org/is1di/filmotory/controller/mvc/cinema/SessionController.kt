package org.is1di.filmotory.controller.mvc.cinema

import org.is1di.filmotory.mapper.cinema.SessionMapper
import org.is1di.filmotory.service.SessionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller("mvcSessionController")
@RequestMapping("cinema/{cinemaId}/session")
class SessionController(
    private val sessionService: SessionService,
    private val sessionMapper: SessionMapper,
) {
    @GetMapping
    fun page(
        @PathVariable cinemaId: UUID,
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int,
        model: Model
    ): String =
        "sessionPage".also {
            model.addAttribute(
                "sessions",
                sessionService.page(cinemaId, page, limit).map { sessionMapper.toOutput(it) })
        }

    @GetMapping("{sessionId}")
    fun session(@PathVariable cinemaId: UUID, @PathVariable sessionId: UUID, model: Model): String =
        "sessionView".also {
            model.addAttribute(
                "session",
                sessionMapper.toOutput(sessionService.getById(cinemaId, sessionId))
            )
        }
}