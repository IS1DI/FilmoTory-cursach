package org.is1di.filmotory.controller.mvc.cinema.admin

import org.is1di.filmotory.mapper.cinema.RegionMapper
import org.is1di.filmotory.model.entity.cinema.RegionDto
import org.is1di.filmotory.service.RegionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller("mvcAdminRegionController")
@RequestMapping("region")
class AdminRegionController(
    private val regionService: RegionService,
    private val regionMapper: RegionMapper,
) {
    @GetMapping("new")
    fun createForm(model: Model): String =
        "regionNew".also { model.addAttribute("region", RegionDto.Create()) }

    @PostMapping
    fun create(@ModelAttribute region: RegionDto.Create): String {
        val regionTitle = regionService.create(regionMapper.toEntity(region)).region
        return "redirect:/region/${regionTitle}"
    }
}