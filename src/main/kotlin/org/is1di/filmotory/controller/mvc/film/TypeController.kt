package org.is1di.filmotory.controller.mvc.film

import org.is1di.filmotory.mapper.film.TypeMapper
import org.is1di.filmotory.service.TypeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcTypeController")
@RequestMapping("type")
class TypeController(
    private val typeService: TypeService,
    private val typeMapper: TypeMapper,
) {
    @GetMapping
    fun list(model: Model): String =
        "typePage".also { model.addAttribute("types", typeService.list().map { typeMapper.toOutput(it) }) }

    @GetMapping("{typeTitle}")
    fun type(@PathVariable typeTitle: String, model: Model): String =
        "typeView".also { model.addAttribute("type", typeMapper.toOutput(typeService.getByTitle(typeTitle))) }

}