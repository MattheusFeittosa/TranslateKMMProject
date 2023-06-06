package com.plcoding.translator_kmm.translate.data

import com.plcoding.translator_kmm.core.language.domain.utils.CommonFlow
import com.plcoding.translator_kmm.core.language.translate.data.HistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.data.HistoryItem
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList

class SqDeLightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {
    private val queries=db.translateQueries
    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries.getHistory().asFlow().mapToList().
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        TODO("Not yet implemented")
    }
}