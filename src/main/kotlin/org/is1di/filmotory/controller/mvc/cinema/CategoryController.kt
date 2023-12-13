package org.is1di.filmotory.controller.mvc.cinema

import org.is1di.filmotory.mapper.cinema.CategoryMapper
import org.is1di.filmotory.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcCategoryController")
@RequestMapping("category")
class CategoryController(
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper,
) {
    @GetMapping
    fun list(model: Model): String =
        "categoryPage".also {
            model.addAttribute(
                "categories",
                categoryService.list().map { categoryMapper.toOutput(it) })
        }

    @GetMapping("{categoryTitle}")
    fun single(@PathVariable categoryTitle: String, model: Model): String =
        "categoryView".also {
            model.addAttribute(
                "category",
                categoryMapper.toOutput(categoryService.getByTitle(categoryTitle))
            )
        }
}