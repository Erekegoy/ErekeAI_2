package com.ereke.ai.ai

import com.ereke.ai.BuildConfig

object GeminiClient {

    suspend fun chat(prompt: String): String {

        if (
            BuildConfig.GEMINI_API_KEY.isBlank() ||
            BuildConfig.GEMINI_API_KEY == "YOUR_KEY_HERE"
        ) {
            return "❌ GEMINI_API_KEY не найден."
        }

        return "🚧 Gemini подключается. API KEY найден. Следующий этап — отправка запроса."
    }
}
