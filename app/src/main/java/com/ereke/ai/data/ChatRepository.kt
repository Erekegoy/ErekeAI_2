package com.ereke.ai.data

import android.net.Uri
import com.ereke.ai.ai.AIRouter
import com.ereke.ai.vision.ImageAnalyzer

class ChatRepository {

    suspend fun ask(prompt: String): String {
        return try {
            AIRouter.chat(prompt)
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }

    suspend fun askImage(uri: Uri): String {
        return try {
            ImageAnalyzer.analyze(uri)
        } catch (e: Exception) {
            "Ошибка анализа изображения: ${e.message}"
        }
    }
}
