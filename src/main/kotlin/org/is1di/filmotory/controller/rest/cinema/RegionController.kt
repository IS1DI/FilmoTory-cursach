package org.is1di.filmotory.controller.rest.cinema

import org.is1di.filmotory.mapper.cinema.RegionMapper
import org.is1di.filmotory.model.entity.cinema.RegionDto
import org.is1di.filmotory.service.RegionService
import org.springframework.web.bind.annotation.*

@RequestMapping("api/region")
@RestController
class RegionController(
    private val regionService: RegionService,
    private val regionMapper: RegionMapper,
) {
    @GetMapping("all")
    fun getAll(): List<RegionDto.Output> =
        regionService.list().map { regionMapper.toOutput(it) }

    @PostMapping
    fun create(@RequestBody region: RegionDto.Create): RegionDto.Output =
        regionMapper.toOutput(regionService.create(regionMapper.toEntity(region)))
}