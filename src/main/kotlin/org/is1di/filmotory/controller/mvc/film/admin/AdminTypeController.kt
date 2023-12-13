package org.is1di.filmotory.controller.mvc.film.admin

import org.is1di.filmotory.mapper.film.TypeMapper
import org.is1di.filmotory.model.entity.film.TypeDto
import org.is1di.filmotory.service.TypeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminTypeController")
@RequestMapping("type")
class AdminTypeController(
    private val typeService: TypeService,
    private val typeMapper: TypeMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "typeNew".also { model.addAttribute("type", TypeDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute type: TypeDto.Create): String {
        val typeTitle = typeService.create(typeMapper.toEntity(type)).type
        return "redirect:/type/$typeTitle"
    }
}