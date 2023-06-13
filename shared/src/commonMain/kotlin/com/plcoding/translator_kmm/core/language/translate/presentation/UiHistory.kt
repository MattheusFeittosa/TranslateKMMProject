package com.plcoding.translator_kmm.core.language.translate.presentation

data class UiHistory(
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLanguage: UiLanguage,
    val toLanguage: UiLanguage
) {
}