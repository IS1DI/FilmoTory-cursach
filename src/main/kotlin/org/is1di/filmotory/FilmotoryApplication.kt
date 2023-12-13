package org.is1di.filmotory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FilmotoryApplication

fun main(args: Array<String>) {
    runApplication<FilmotoryApplication>(*args)
}
