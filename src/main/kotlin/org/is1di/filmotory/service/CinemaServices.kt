package org.is1di.filmotory.service

import org.is1di.filmotory.NotFoundException
import org.is1di.filmotory.message.MessageBase
import org.is1di.filmotory.model.entity.cinema.Category
import org.is1di.filmotory.model.entity.cinema.Cinema
import org.is1di.filmotory.model.entity.cinema.Region
import org.is1di.filmotory.model.entity.cinema.Session
import org.is1di.filmotory.repository.CategoryRepository
import org.is1di.filmotory.repository.CinemaRepository
import org.is1di.filmotory.repository.RegionRepository
import org.is1di.filmotory.repository.SessionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CinemaService(
    private val cinemaRepository: CinemaRepository,
    private val categoryService: CategoryService,
    private val regionService: RegionService,
) {
    fun create(cinema: Cinema): Cinema =
        cinemaRepository.save(cinema.apply {
            this.category = categoryService.getByTitle(cinema.category.category)
            this.region = regionService.getByRegion(region.region)
        })

    fun getById(id: UUID): Cinema =
        cinemaRepository.findById(id)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.CINEMA_NOT_FOUND)) }

    fun search(title: String, page: Int = 1, limit: Int = 5): Page<Cinema> =
        cinemaRepository.findByNameLikeIgnoreCase(
            title,
            PageRequest.of(1.coerceAtLeast(page) - 1, 1.coerceAtLeast(limit))
        )

    fun <DTO> update(id: UUID, dto: DTO, mapper: (DTO, Cinema) -> Cinema): Cinema =
        cinemaRepository.save(mapper(dto, getById(id)))

    fun page(page: Int = 1, limit: Int = 15): Page<Cinema> =
        cinemaRepository.findAll(PageRequest.of(1.coerceAtLeast(page) - 1, 1.coerceAtLeast(limit)))

    fun top(page: Int = 1, limit: Int = 10): Page<Cinema> =
        cinemaRepository.top(PageRequest.of(1.coerceAtLeast(page) - 1, limit))
}

@Service
@Transactional
class SessionService(
    private val sessionRepository: SessionRepository,
    private val cinemaService: CinemaService,
    private val filmService: FilmService
) {
    fun getById(cinemaId: UUID, id: UUID): Session =
        sessionRepository.findByCinema_IdAndId(cinemaId, id)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.SESSION_NOT_FOUND)) }

    fun create(cinemaId: UUID, session: Session): Session =
        sessionRepository.save(session.apply {
            cinema = cinemaService.getById(cinemaId)
            film = filmService.getById(film.id!!)
        })

    fun page(cinemaId: UUID, page: Int = 1, limit: Int = 15): Page<Session> =
        sessionRepository.findAll(PageRequest.of(1.coerceAtLeast(page) - 1, 1.coerceAtLeast(limit)))

    fun upcoming(page: Int = 1, limit: Int = 15): Page<Session> =
        sessionRepository.upcoming(PageRequest.of(1.coerceAtLeast(page), 1.coerceAtLeast(limit)))
}

@Service
@Transactional
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {
    fun getByTitle(category: String): Category =
        categoryRepository.findById(category)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.CATEGORY_NOT_FOUND)) }

    fun create(category: Category): Category =
        categoryRepository.save(category)

    fun list(): List<Category> =
        categoryRepository.findAll()
}

@Service
@Transactional
class RegionService(
    private val regionRepository: RegionRepository,
) {
    fun getByRegion(region: String): Region =
        regionRepository.findById(region)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.REGION_NOT_FOUND)) }

    fun create(region: Region): Region =
        regionRepository.save(region)

    fun list(): List<Region> =
        regionRepository.findAll()
}