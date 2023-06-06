package com.plcoding.translator_kmm.core.language.translate.data.translate

import com.plcoding.translator_kmm.core.language.Language

interface TranslateClient {

    suspend fun translate(
        fromLanguage: Language,
        fromText:String,
        toLanguage: Language
    ):String
}