package com.plcoding.translator_kmm.core.language.translate.presentation

import com.plcoding.translator_kmm.core.language.Language

expect class UiLanguage {
    val language: Language

    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguage: List<UiLanguage>
    }

}