package org.is1di.filmotory.controller.mvc.cinema

import org.is1di.filmotory.mapper.cinema.RegionMapper
import org.is1di.filmotory.service.RegionService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("region")
@Controller("mvcRegionController")
class RegionController(
    private val regionService: RegionService,
    private val regionMapper: RegionMapper,
) {
    @GetMapping
    fun list(model: Model): String =
        "regionPage".also { model.addAttribute("regions", regionService.list().map { regionMapper.toOutput(it) }) }

    @GetMapping("{regionTitle}")
    fun region(@PathVariable regionTitle: String, model: Model): String =
        "regionView".also {
            model.addAttribute(
                "region",
                regionMapper.toOutput(regionService.getByRegion(regionTitle))
            )
        }
}