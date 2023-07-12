package com.plcoding.translator_kmm.android.voice_to_text.domain

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.plcoding.translator_kmm.core.language.domain.utils.CommonStateFlow
import com.plcoding.translator_kmm.core.language.domain.utils.toCommonStateFlow
import com.plcoding.translator_kmm.voice_to_text.domain.VoiceToTextParse
import com.plcoding.translator_kmm.voice_to_text.domain.VoiceToTextParserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class AndroidVoiceToText (private val app: Application) : VoiceToTextParse, RecognitionListener {

    private val recognize = SpeechRecognizer.createSpeechRecognizer(app)
    private val _state = MutableStateFlow(VoiceToTextParserState())
    override val state: CommonStateFlow<VoiceToTextParserState> =_state.toCommonStateFlow()

    override fun startListener(languageCode: String) {
        _state.update { VoiceToTextParserState() }

        if (!SpeechRecognizer.isRecognitionAvailable(app)) {
            _state.update {
                it.copy(
                    error = "Speech recognition is not available"
                )
            }
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
            }
            recognize.setRecognitionListener(this)
            recognize.startListening(intent)
            _state.update { it.copy(isSpeaking = true) }
        }
    }

    override fun stopListener() {
        _state.update { VoiceToTextParserState() }
        recognize.stopListening()
    }

    override fun cancel() {
        recognize.cancel()
    }

    override fun reset() {
        _state.value = VoiceToTextParserState()
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        _state.update { it.copy(error = null) }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(p0: Float) {
        _state.update {
            it.copy(powerRatio = p0 * (1f / (12f - (-2f))))
        }
    }

    override fun onBufferReceived(p0: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update { it.copy(isSpeaking = false) }
    }

    override fun onError(p0: Int) {
        _state.update { it.copy(error = "Error:$p0") }
    }

    override fun onResults(result: Bundle?) {
        result
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { text ->
                _state.update {
                    it.copy(
                        result = text
                    )
                }
            }
    }

    override fun onPartialResults(p0: Bundle?)= Unit

    override fun onEvent(p0: Int, p1: Bundle?)=Unit

}