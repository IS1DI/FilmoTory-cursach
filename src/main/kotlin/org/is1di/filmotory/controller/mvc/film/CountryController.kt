package org.is1di.filmotory.controller.mvc.film

import org.is1di.filmotory.mapper.film.CountryMapper
import org.is1di.filmotory.service.CountryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcCountryController")
@RequestMapping("country")
class CountryController(
    private val countryService: CountryService,
    private val countryMapper: CountryMapper,
) {
    @GetMapping
    fun list(model: Model): String =
        "countryPage".also { model.addAttribute("countries", countryService.list().map { countryMapper.toOutput(it) }) }

    @GetMapping("{countryTitle}")
    fun country(@PathVariable countryTitle: String, model: Model): String =
        "countryView".also {
            model.addAttribute(
                "country",
                countryMapper.toOutput(countryService.getByTitle(countryTitle))
            )
        }
}