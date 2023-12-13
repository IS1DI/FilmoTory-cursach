package org.is1di.filmotory.controller.mvc

import org.is1di.filmotory.AlreadyExistsException
import org.is1di.filmotory.NotFoundException
import org.is1di.filmotory.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime

@ControllerAdvice(name = "mvcExceptionController")
class ExceptionController(
    private val messageService: MessageService,
) {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFound(exception: NotFoundException) =
        messageService.toMessage(exception).toExMessage()

    @ExceptionHandler(AlreadyExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun alreadyExists(exception: AlreadyExistsException) =
        messageService.toMessage(exception).toExMessage()

}

class ExceptionMessage(
    val message: String,
    val timeStamp: LocalDateTime
)

fun String.toExMessage() =
    ExceptionMessage(
        this,
        LocalDateTime.now()
    )