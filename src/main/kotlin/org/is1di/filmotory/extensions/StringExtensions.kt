package org.is1di.filmotory.extensions

import java.util.*

fun String.toUUID(): UUID =
    UUID.fromString(this)
