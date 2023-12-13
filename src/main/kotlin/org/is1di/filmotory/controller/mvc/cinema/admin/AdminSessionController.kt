package org.is1di.filmotory.controller.mvc.cinema.admin

import org.is1di.filmotory.mapper.cinema.SessionMapper
import org.is1di.filmotory.model.entity.cinema.SessionDto
import org.is1di.filmotory.service.SessionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("cinema/{cinemaId}/session")
@Controller("mvcAdminSessionController")
class AdminSessionController(
    private val sessionService: SessionService,
    private val sessionMapper: SessionMapper,
) {
    @GetMapping("new")
    fun createForm(@PathVariable cinemaId: UUID, model: Model): String =
        "cinemaNew".also {
            model.addAttribute("session", SessionDto.Create().also { it.cinemaId = cinemaId })
        }

    @PostMapping
    fun create(@ModelAttribute session: SessionDto.Create, @PathVariable cinemaId: UUID, model: Model): String {
        val sessionId = sessionService.create(cinemaId, sessionMapper.toEntity(session)).id
        return "redirect:/cinema/${cinemaId}/session/${sessionId}"
    }
}