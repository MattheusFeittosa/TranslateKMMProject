package com.plcoding.translator_kmm.core.language.translate.domain.translate

enum class TranslateErro() {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class TranslateException(val error: TranslateErro) :
    Exception("An error occurred when translating $error")