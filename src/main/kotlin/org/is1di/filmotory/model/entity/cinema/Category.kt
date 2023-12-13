package org.is1di.filmotory.model.entity.cinema

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Category(
    @Id var category: String,
    var countSeats: Int,
    var ticketPriceMultiplier: Double
)

class CategoryDto {

    class Create(
        var category: String = "",
        var countSeats: Int = 0,
        var ticketPriceMultiplier: Double = 0.0
    )

    class Update(
        var category: String?,
        var countSeats: Int?,
        var ticketPriceMultiplier: Double?
    )

    class Output(
        var category: String,
        var countSeats: Int,
        var ticketPriceMultiplier: Double
    )
}