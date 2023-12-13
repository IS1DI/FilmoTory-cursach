package org.is1di.filmotory.repository

import org.is1di.filmotory.model.entity.film.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FilmRepository : JpaRepository<Film, UUID> {

    fun findByTitleLikeIgnoreCase(title: String, pageable: Pageable): Page<Film>


    fun findByIsEnabledTrueOrderByPublishDateAsc(pageable: Pageable): Page<Film>
}

interface AuthorRepository : JpaRepository<Author, UUID>

interface GenreRepository : JpaRepository<Genre, String>

interface CountryRepository : JpaRepository<Country, String>

interface TypeRepository : JpaRepository<Type, String>