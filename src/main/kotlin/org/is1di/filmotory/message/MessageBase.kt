package org.is1di.filmotory.message

class MessageBase(
    val method: MessageMethod,
    var args: Array<Any>? = null,
) {
    enum class MessageMethod(
        val message: String
    ) {
        CATEGORY_NOT_FOUND("category.error.notFound"),
        CINEMA_NOT_FOUND("cinema.error.notFound"),
        REGION_NOT_FOUND("region.error.notFound"),
        SESSION_NOT_FOUND("session.error.notFound"),
        AUTHOR_NOT_FOUND("author.error.notFound"),
        COUNTRY_NOT_FOUND("country.error.notFound"),
        FILM_NOT_FOUND("film.error.notFound"),
        GENRE_NOT_FOUND("genre.error.notFound"),
        FILM_TYPE_NOT_FOUND("film.type.error.notFound"),
        TYPE_ALREADY_EXISTS("type.error.exists"),
        COUNTRY_ALREADY_EXISTS("country.error.exists"),
        GENRE_ALREADY_EXISTS("genre.error.exists"),
    }
}