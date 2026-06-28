package com.ereke.ai.data

import com.ereke.ai.ai.GroqClient

class ChatRepository {

    fun ask(prompt: String): String {
        return try {
            GroqClient.chat(prompt)
        } catch (e: Exception) {
            "Ошибка: ${e.message}"
        }
    }

}
