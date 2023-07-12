package com.plcoding.translator_kmm.android.voice_to_text.domain.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.translator_kmm.android.voice_to_text.domain.AndroidVoiceToText
import com.plcoding.translator_kmm.voice_to_text.domain.VoiceToTextParse
import com.plcoding.translator_kmm.voice_to_text.domain.presentation.VoiceToTextEvent
import com.plcoding.translator_kmm.voice_to_text.domain.presentation.VoiceToTextViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextViewModel @Inject constructor(
    private val parser: VoiceToTextParse
): ViewModel() {

    private val viewModel by lazy {
        VoiceToTextViewModel(
            parser = parser,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event)
    }
}