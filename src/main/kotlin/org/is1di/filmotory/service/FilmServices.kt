package org.is1di.filmotory.service

import org.is1di.filmotory.AlreadyExistsException
import org.is1di.filmotory.NotFoundException
import org.is1di.filmotory.message.MessageBase
import org.is1di.filmotory.model.entity.film.*
import org.is1di.filmotory.repository.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class FilmService(
    private val filmRepository: FilmRepository,
    private val typeService: TypeService,
    private val countryService: CountryService,
    private val genreService: GenreService,
) {
    fun create(film: Film): Film =
        filmRepository.save(film.apply {
            type = typeService.getByTitle(type.type)
            country = countryService.getByTitle(country.country)
            genres = genres.map { genreService.get(it.name) }.toCollection(mutableListOf())
        })

    fun getById(id: UUID): Film =
        filmRepository.findById(id)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.FILM_NOT_FOUND)) }

    fun search(title: String, page: Int = 1, limit: Int = 5): Page<Film> =
        filmRepository.findByTitleLikeIgnoreCase(title, PageRequest.of(1.coerceAtLeast(page), 1.coerceAtLeast(limit)))

    fun page(page: Int = 1, limit: Int = 15): Page<Film> =
        filmRepository.findAll(PageRequest.of(1.coerceAtLeast(page) - 1, 1.coerceAtLeast(limit)))

    fun getAll(): MutableList<Film> = filmRepository.findAll()

    fun top(page: Int = 1, limit: Int = 10) = filmRepository.findByIsEnabledTrueOrderByPublishDateAsc(
        PageRequest.of(
            1.coerceAtLeast(page) - 1,
            1.coerceAtLeast(limit)
        )
    )
}

@Service
@Transactional
class AuthorService(
    private val authorRepository: AuthorRepository,
) {
    fun create(author: Author): Author =
        authorRepository.save(author)

    fun getById(id: UUID): Author =
        authorRepository.findById(id)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.AUTHOR_NOT_FOUND)) }

    fun list(): List<Author> =
        authorRepository.findAll()

    fun page(page: Int = 1, limit: Int = 15): Page<Author> =
        authorRepository.findAll(PageRequest.of(1.coerceAtLeast(page) - 1, 1.coerceAtLeast(limit)))
}

@Service
@Transactional
class GenreService(
    private val genreRepository: GenreRepository
) {
    fun exists(genre: String) =
        genreRepository.existsById(genre)

    fun create(genre: Genre): Genre =
        if (exists(genre.name)) genreRepository.save(genre)
        else throw AlreadyExistsException(MessageBase(MessageBase.MessageMethod.GENRE_ALREADY_EXISTS))

    fun get(genre: String): Genre =
        genreRepository.findById(genre)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.GENRE_NOT_FOUND)) }

    fun list(): List<Genre> =
        genreRepository.findAll()
}

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository
) {
    fun exists(country: String) =
        countryRepository.existsById(country)

    fun create(country: Country): Country =
        if (!exists(country.country)) countryRepository.save(country)
        else throw AlreadyExistsException(
            MessageBase(MessageBase.MessageMethod.COUNTRY_ALREADY_EXISTS, arrayOf(country.country))
        )

    fun getByTitle(country: String): Country =
        countryRepository.findById(country)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.COUNTRY_NOT_FOUND)) }

    fun list(): List<Country> =
        countryRepository.findAll()
}

@Service
@Transactional
class TypeService(
    private val typeRepository: TypeRepository
) {
    fun exists(type: String) =
        typeRepository.existsById(type)

    fun create(type: Type): Type =
        if (!exists(type.type)) typeRepository.save(type) else
            throw AlreadyExistsException(
                MessageBase(MessageBase.MessageMethod.TYPE_ALREADY_EXISTS, arrayOf(type.type))
            )

    fun getByTitle(type: String): Type =
        typeRepository.findById(type)
            .orElseThrow { NotFoundException(MessageBase(MessageBase.MessageMethod.FILM_TYPE_NOT_FOUND)) }

    fun list(): List<Type> =
        typeRepository.findAll()
}

