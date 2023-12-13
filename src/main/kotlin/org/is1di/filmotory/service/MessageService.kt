package org.is1di.filmotory.service

import org.is1di.filmotory.BaseException
import org.is1di.filmotory.message.MessageBase
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val messageSource: MessageSource,
) {
    fun getMessage(message: MessageBase): String =
        messageSource.getMessage(message.method.message, message.args, LocaleContextHolder.getLocale())

    fun toMessage(ex: BaseException): String =
        getMessage(ex.messageBase)
}