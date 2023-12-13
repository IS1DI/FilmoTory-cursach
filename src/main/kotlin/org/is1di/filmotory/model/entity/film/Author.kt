package org.is1di.filmotory.model.entity.film

import jakarta.persistence.*
import java.util.*

@Entity
class Author(
    var name: String,
    var lastName: String,
    @OneToMany(mappedBy = "author") var films: MutableList<Film> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: UUID? = null,
)

class AuthorDto {
    class Create(
        var name: String = "",
        var lastName: String = ""
    )

    class Update(
        var name: String,
        var lastName: String
    )

    class Output(
        var name: String,
        var lastName: String,
        var films: List<UUID> = mutableListOf(),
        var id: UUID
    )
}