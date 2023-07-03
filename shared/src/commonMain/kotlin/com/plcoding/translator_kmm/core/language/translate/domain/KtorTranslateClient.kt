package com.plcoding.translator_kmm.core.language.translate.domain

import com.plcoding.translator_kmm.NetworkConstants
import com.plcoding.translator_kmm.core.language.Language
import com.plcoding.translator_kmm.core.language.translate.data.translate.TranslatedDto
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateErro
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateException
import com.plcoding.translator_kmm.core.language.translate.data.translate.TranslateDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

class KtorTranslateClient(
    private val httpClient:HttpClient
) : TranslateClient {

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        val result = try {
            httpClient.post {
                url(NetworkConstants.BASE_URL + "/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        } catch (e: IOException) {
            throw TranslateException(TranslateErro.SERVICE_UNAVAILABLE)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw TranslateException(TranslateErro.SERVER_ERROR)
            in 400..499 -> throw TranslateException(TranslateErro.CLIENT_ERROR)
            else -> throw TranslateException(TranslateErro.UNKNOWN_ERROR)
        }

        return try {
            result.body<TranslatedDto>().translatedText
        } catch (e: Exception) {
            throw TranslateException(TranslateErro.SERVER_ERROR)
        }
    }
}