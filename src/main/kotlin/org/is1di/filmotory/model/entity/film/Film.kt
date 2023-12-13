package org.is1di.filmotory.model.entity.film

import jakarta.persistence.*
import org.is1di.filmotory.model.entity.cinema.Session
import java.time.Duration
import java.time.LocalDate
import java.util.*

@Entity
class Film(
    var title: String,
    @ManyToOne @JoinColumn var author: Author,
    var duration: Duration,
    @ManyToOne var country: Country,
    @ManyToOne @JoinColumn(name = "type") var type: Type,
    var price: Double,
    var isEnabled: Boolean,
    @Column(name = "local_date", columnDefinition = "DATE") var publishDate: LocalDate,
    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(
        name = "film_genre",
        joinColumns = [JoinColumn(name = "film_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id", referencedColumnName = "name")]
    ) var genres: MutableList<Genre> = mutableListOf(),
    @OneToMany(mappedBy = "film") var sessions: MutableList<Session> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: UUID? = null,
)

class FilmDto {
    class Create(
        var title: String = "",
        var authorId: UUID? = null,
        var duration: Duration = Duration.ZERO,
        var country: String = "",
        var publishDate: LocalDate = LocalDate.now(),
        var type: String = "",
        var price: Double = 0.0,
        var genres: MutableSet<Genre> = mutableSetOf(),
    )

    class Output(
        var title: String,
        var author: AuthorDto.Output,
        var duration: Duration,
        var country: Country,
        var type: Type,
        var price: Double,
        var isEnabled: Boolean,
        var publishDate: LocalDate,
        var genres: List<GenreDto.Output> = mutableListOf(),
        var sessions: List<UUID> = mutableListOf(),
        var id: UUID,
    )
}