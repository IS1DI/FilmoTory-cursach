package org.is1di.filmotory.model.entity.cinema

import jakarta.persistence.*
import java.util.*

@Entity
class Cinema(
    @Column(unique = true) var name: String,
    @Column(unique = true) var address: String,
    @ManyToOne @JoinColumn(name = "region") var region: Region,
    @ManyToOne var category: Category,
    @OneToMany(mappedBy = "cinema") var sessions: MutableList<Session> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: UUID? = null,
)

class CinemaDto {
    class Create(
        var name: String = "",
        var address: String = "",
        var region: String = "",
        var category: String = "",
        var countSeats: Int = 0,
        var ticketPriceMultiplier: Double = 0.0
    )

    class Update(
        var name: String?,
        var category: CategoryDto.Update?,
    )

    class Output(
        var name: String,
        var address: String,
        var region: String,
        var category: CategoryDto.Output,
        var sessions: List<UUID> = mutableListOf(),
        var id: UUID
    )
}