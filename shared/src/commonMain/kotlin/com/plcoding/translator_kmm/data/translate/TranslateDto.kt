package com.plcoding.translator_kmm.data.translate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateDto(
    @SerialName("textToTranslate") val textToTranslate: String,
    @SerialName("sourceLanguage") val sourceLanguageCode: String,
    @SerialName("targetLanguage") val targetLanguageCode: String
)
