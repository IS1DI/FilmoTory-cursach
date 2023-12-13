package org.is1di.filmotory.controller.mvc.film

import org.is1di.filmotory.mapper.film.AuthorMapper
import org.is1di.filmotory.service.AuthorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller("mvcAuthorController")
@RequestMapping("author")
class AuthorController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper,
) {
    @GetMapping
    fun page(
        @RequestParam(name = "p", defaultValue = "1") page: Int,
        @RequestParam(name = "l", defaultValue = "15") limit: Int,
        model: Model
    ): String =
        "authorPage".also {
            model.addAttribute(
                "authors",
                authorService.page(page, limit).map { authorMapper.toOutput(it) })
        }

    @GetMapping("{id}")
    fun author(@PathVariable id: UUID, model: Model): String =
        "authorView".also { model.addAttribute("author", authorMapper.toOutput(authorService.getById(id))) }
}