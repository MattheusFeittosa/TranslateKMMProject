package com.plcoding.translator_kmm.core.language.domain.utils

sealed class Resources<T>(val data: T?, val throwable: Throwable? = null) {
    class Success<T>(data: T?) : Resources<T>(data)
    class Error<T>(exception: Throwable) : Resources<T>(null, exception)
}
