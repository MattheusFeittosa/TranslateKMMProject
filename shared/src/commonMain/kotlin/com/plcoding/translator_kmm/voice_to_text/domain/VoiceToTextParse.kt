package com.plcoding.translator_kmm.voice_to_text.domain

import com.plcoding.translator_kmm.core.language.domain.utils.CommonStateFlow

interface VoiceToTextParse {
    val state:CommonStateFlow<VoiceToTextParserState>
    fun startListener(languageCode:String)
    fun stopListener()
    fun cancel()
    fun reset()
}