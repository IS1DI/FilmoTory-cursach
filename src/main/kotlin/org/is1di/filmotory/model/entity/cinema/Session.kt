package org.is1di.filmotory.model.entity.cinema

import jakarta.persistence.*
import org.is1di.filmotory.model.entity.film.Film
import java.time.LocalDateTime
import java.util.*

@Entity
class Session(
    var occupiedSeats: Int,
    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP") var startAt: LocalDateTime,
    @ManyToOne @JoinColumn var film: Film,
    @ManyToOne @JoinColumn var cinema: Cinema,
    @Id @GeneratedValue(strategy = GenerationType.UUID) val id: UUID? = null,
)

class SessionDto {
    class Create(
        var occupiedSeats: Int = 0,
        var startAt: LocalDateTime = LocalDateTime.now(),
        var filmId: UUID? = null,
        var cinemaId: UUID? = null
    )

    class Output(
        var occupiedSeats: Int,
        var startAt: LocalDateTime,
        var filmId: UUID,
        var cinemaId: UUID,
        var id: UUID,
    )
}