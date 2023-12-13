package org.is1di.filmotory.model.entity.film

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.*

@Entity
class Genre(
    @Id var name: String,
    @ManyToMany(mappedBy = "genres") var films: MutableList<Film> = mutableListOf(),
)

class GenreDto {
    class Create(
        var name: String = "",
    )

    class Output(
        var name: String,
        var films: List<UUID> = mutableListOf(),
    )
}