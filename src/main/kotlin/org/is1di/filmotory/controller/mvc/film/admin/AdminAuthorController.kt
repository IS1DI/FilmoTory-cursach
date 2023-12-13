package org.is1di.filmotory.controller.mvc.film.admin

import org.is1di.filmotory.mapper.film.AuthorMapper
import org.is1di.filmotory.model.entity.film.AuthorDto
import org.is1di.filmotory.service.AuthorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminAuthorController")
@RequestMapping("author")
class AdminAuthorController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "authorNew".also { model.addAttribute("author", AuthorDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute author: AuthorDto.Create): String {
        val id = authorService.create(authorMapper.toEntity(author)).id
        return "redirect:/author/${id}"
    }
}