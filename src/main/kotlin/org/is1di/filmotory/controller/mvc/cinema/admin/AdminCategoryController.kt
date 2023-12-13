package org.is1di.filmotory.controller.mvc.cinema.admin

import org.is1di.filmotory.mapper.cinema.CategoryMapper
import org.is1di.filmotory.model.entity.cinema.CategoryDto
import org.is1di.filmotory.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminCategoryController")
@RequestMapping("category")
class AdminCategoryController(
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "categoryNew".also { model.addAttribute("category", CategoryDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute category: CategoryDto.Create): String {
        val categoryTitle = categoryService.create(categoryMapper.toEntity(category)).category
        return "redirect:category/${categoryTitle}"
    }
}