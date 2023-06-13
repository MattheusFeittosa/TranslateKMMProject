package com.plcoding.translator_kmm.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.translator_kmm.core.language.translate.data.history.Translate
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.presentation.TranslateEvent
import com.plcoding.translator_kmm.core.language.translate.presentation.viewModel.TranslatedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidViewModelTranslate @Inject constructor(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource
) : ViewModel() {

    private val viewModel by lazy {
        TranslatedViewModel(
            translate = translate,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    fun onEvent(event: TranslateEvent) {
        viewModel.onEvent(event)
    }
}