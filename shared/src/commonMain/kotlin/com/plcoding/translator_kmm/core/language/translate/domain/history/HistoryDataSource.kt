package com.plcoding.translator_kmm.core.language.translate.domain.history

import com.plcoding.translator_kmm.core.language.domain.utils.CommonFlow
import com.plcoding.translator_kmm.core.language.translate.data.HistoryItem

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}