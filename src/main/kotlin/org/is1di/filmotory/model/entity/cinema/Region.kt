package org.is1di.filmotory.model.entity.cinema

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Region(
    @Id var region: String,
    @OneToMany(mappedBy = "region") var cinemas: MutableList<Cinema> = mutableListOf()
)

class RegionDto {
    class Create(
        var region: String = ""
    )

    class Output(
        var region: String
    )
}