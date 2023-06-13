package com.plcoding.translator_kmm.core.language.translate.data.history

import com.plcoding.translator_kmm.core.language.Language
import com.plcoding.translator_kmm.core.language.domain.utils.Resources
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateException

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {

    suspend fun execute(
        fromLanguage: Language,
        toLanguage: Language,
        text: String
    ): Resources<String> {
        return try {
            val result = client.translate(fromLanguage, text, toLanguage)
            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = text,
                    toLanguageCode = toLanguage.langCode,
                    toText = result
                )
            )
            return Resources.Success(result)
        } catch (e: TranslateException) {
            return Resources.Error(e)
        }

    }
}