package com.ereke.ai.data

import com.ereke.ai.ai.AIRouter

class ChatRepository {

    suspend fun ask(prompt: String): String {
        return try {
            AIRouter.chat(prompt)
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }
}
