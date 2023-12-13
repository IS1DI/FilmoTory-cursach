package org.is1di.filmotory.model.entity.film

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Country(@Id var country: String)

class CountryDto {
    class Create(
        var country: String = ""
    )

    class Output(
        var country: String
    )
}