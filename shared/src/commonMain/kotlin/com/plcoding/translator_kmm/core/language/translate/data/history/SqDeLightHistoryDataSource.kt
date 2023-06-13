package com.plcoding.translator_kmm.core.language.translate.data.history

import com.plcoding.translator_kmm.core.language.domain.utils.CommonFlow
import com.plcoding.translator_kmm.core.language.domain.utils.toCommonFlow
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryItem
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqDeLightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {
    private val queries = db.translateQueries
    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries.getHistory().asFlow().mapToList().map { history ->
            history.map {
                it.toHistoryItem()
            }
        }.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}