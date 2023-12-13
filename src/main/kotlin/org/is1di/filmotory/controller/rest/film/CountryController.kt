package org.is1di.filmotory.controller.rest.film

import org.is1di.filmotory.mapper.film.CountryMapper
import org.is1di.filmotory.model.entity.film.CountryDto
import org.is1di.filmotory.service.CountryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/country")
class CountryController(
    private val countryService: CountryService,
    private val countryMapper: CountryMapper
) {
    @GetMapping("all")
    fun getAll(): List<CountryDto.Output> =
        countryService.list().map { countryMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody country: CountryDto.Create): CountryDto.Output =
        countryMapper.toOutput(countryService.create(countryMapper.toEntity(country)))
}