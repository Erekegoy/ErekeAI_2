package com.ereke.ai.data

import com.ereke.ai.ai.AIRouter

class ChatRepository {

    fun ask(prompt: String): String {
        return try {
            AIRouter.chat(prompt)
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }
}
