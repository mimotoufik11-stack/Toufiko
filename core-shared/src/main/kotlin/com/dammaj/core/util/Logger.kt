package com.dammaj.core.util

object Logger {
    fun log(tag: String, message: String) {
        println("[$tag] $message")
    }

    fun error(tag: String, message: String, throwable: Throwable? = null) {
        println("ERROR [$tag] $message")
        throwable?.printStackTrace()
    }
}
