package org.is1di.filmotory.controller.rest.cinema

import org.is1di.filmotory.mapper.cinema.CategoryMapper
import org.is1di.filmotory.model.entity.cinema.CategoryDto
import org.is1di.filmotory.service.CategoryService
import org.springframework.web.bind.annotation.*

@RequestMapping("api/category")
@RestController
class CategoryController(
    private val categoryService: CategoryService,
    private val categoryMapper: CategoryMapper,
) {
    @GetMapping("all")
    fun getAll(): List<CategoryDto.Output> =
        categoryService.list().map { categoryMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody category: CategoryDto.Create): CategoryDto.Output =
        categoryMapper.toOutput(categoryService.create(categoryMapper.toEntity(category)))
}