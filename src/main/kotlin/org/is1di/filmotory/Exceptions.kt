package org.is1di.filmotory

import org.is1di.filmotory.message.MessageBase

open class BaseException(open val messageBase: MessageBase) : RuntimeException()

class NotFoundException(override val messageBase: MessageBase) : BaseException(messageBase)

class AlreadyExistsException(override val messageBase: MessageBase) : BaseException(messageBase)