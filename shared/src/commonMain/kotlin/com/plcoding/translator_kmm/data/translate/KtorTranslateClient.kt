package com.plcoding.translator_kmm.data.translate

import com.plcoding.translator_kmm.NetworkConstants
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.translate.domain.translate.TranslateErro
import com.plcoding.translator_kmm.translate.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorTranslateClient(
    val httpClient: HttpClient
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
            else -> {
                throw TranslateException(TranslateErro.UNKNOWN_ERROR)
            }

        }
        return try {
            result.body<TranslatedDto>().translatedText
        } catch (e: Exception) {
            throw TranslateException(TranslateErro.SERVER_ERROR)
        }
    }
}