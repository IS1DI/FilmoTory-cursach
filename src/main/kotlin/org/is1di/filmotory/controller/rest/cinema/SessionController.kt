package org.is1di.filmotory.controller.rest.cinema

import org.is1di.filmotory.mapper.cinema.SessionMapper
import org.is1di.filmotory.model.entity.cinema.SessionDto
import org.is1di.filmotory.service.SessionService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/cinema/{id}/session")
@RestController
class SessionController(
    private val sessionService: SessionService,
    private val sessionMapper: SessionMapper,
) {
    @GetMapping
    fun getAll(
        @PathVariable id: UUID,
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int,
    ): Page<SessionDto.Output> =
        sessionService.page(id, page, limit).map { sessionMapper.toOutput(it) }

    @PostMapping
    fun create(
        @PathVariable id: UUID,
        @RequestBody session: SessionDto.Create,
    ): SessionDto.Output =
        sessionMapper.toOutput(sessionService.create(id, sessionMapper.toEntity(session)))

    @GetMapping("{sessionId}")
    fun get(
        @PathVariable id: UUID,
        @PathVariable sessionId: UUID,
    ): SessionDto.Output =
        sessionMapper.toOutput(sessionService.getById(id, sessionId))
}