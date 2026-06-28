package com.ereke.ai.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

object TTSManager {

    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        if (tts != null) return

        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("ru", "RU")
                tts?.setSpeechRate(1.0f)
                tts?.setPitch(1.0f)
            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ErekeAI")
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
