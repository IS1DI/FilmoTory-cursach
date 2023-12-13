package org.is1di.filmotory.repository

import org.is1di.filmotory.model.entity.cinema.Category
import org.is1di.filmotory.model.entity.cinema.Cinema
import org.is1di.filmotory.model.entity.cinema.Region
import org.is1di.filmotory.model.entity.cinema.Session
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CinemaRepository : JpaRepository<Cinema, UUID> {
    fun findByName(title: String): Optional<Cinema>

    @Query("select c from Cinema c where upper(c.name) like upper(?1)")
    fun findByNameLikeIgnoreCase(name: String, pageable: Pageable): Page<Cinema>

    @Query("select c from Cinema c left join c.sessions s left join c.category cat group by c order by  avg((cat.countSeats - s.occupiedSeats) / cat.countSeats) desc, count(s) asc")
    fun top(pageable: Pageable): Page<Cinema>
}

interface SessionRepository : JpaRepository<Session, UUID> {

    fun findByCinema_IdAndId(cinemaId: UUID, id: UUID): Optional<Session>

    @Query("select s from Session s where local datetime < (s.startAt + s.film.duration ) order by s.startAt desc")
    fun upcoming(pageable: Pageable): Page<Session>
}

interface CategoryRepository : JpaRepository<Category, String>

interface RegionRepository : JpaRepository<Region, String>