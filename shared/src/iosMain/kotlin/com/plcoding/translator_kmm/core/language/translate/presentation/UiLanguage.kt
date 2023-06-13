package com.plcoding.translator_kmm.core.language.translate.presentation

import com.plcoding.translator_kmm.core.language.Language

actual class UiLanguage(
    actual val language: Language,
    val imageName: String
) {
    actual companion object {
        actual val allLanguage: List<UiLanguage>
            get() = Language.values().map {
                UiLanguage(
                    it,
                    it.langName.lowercase()
                )
            }

        actual fun byCode(langCode: String): UiLanguage {
            return allLanguage.find {
                it.language.langCode == langCode
            } ?: throw IllegalArgumentException("None Language found")
        }
    }

}