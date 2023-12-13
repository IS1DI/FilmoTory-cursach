package org.is1di.filmotory.model.entity.film

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Type(
    @Id var type: String,
    @OneToMany(mappedBy = "type") var films: MutableList<Film> = mutableListOf(),
)

class TypeDto {
    class Create(
        var type: String = ""
    )

    class Output(
        var type: String
    )
}