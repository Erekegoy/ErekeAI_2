package com.ereke.ai.voice

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent

object VoiceRecognizer {

    const val REQUEST_CODE = 1001

    fun createIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                "ru-RU"
            )
            putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Говорите..."
            )
        }
    }

    fun start(activity: Activity) {
        activity.startActivityForResult(
            createIntent(),
            REQUEST_CODE
        )
    }
}
