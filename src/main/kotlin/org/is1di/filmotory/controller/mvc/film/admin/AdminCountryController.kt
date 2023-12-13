package org.is1di.filmotory.controller.mvc.film.admin

import org.is1di.filmotory.mapper.film.CountryMapper
import org.is1di.filmotory.model.entity.film.CountryDto
import org.is1di.filmotory.service.CountryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminCountryController")
@RequestMapping("country")
class AdminCountryController(
    private val countryService: CountryService,
    private val countryMapper: CountryMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "countryNew".also { model.addAttribute("country", CountryDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute country: CountryDto.Create): String {
        val countryTitle = countryService.create(countryMapper.toEntity(country)).country
        return "redirect:/country/${countryTitle}"
    }
}