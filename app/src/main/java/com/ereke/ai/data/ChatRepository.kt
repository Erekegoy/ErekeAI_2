package com.ereke.ai.data

import com.ereke.ai.network.GroqApiService
import kotlinx.coroutines.delay

class ChatRepository {
    private val api = GroqApiService.create()

    suspend fun sendMessage(text: String): String {
        // simple placeholder: send to Groq API and return assistant reply
        return try {
            val resp = api.sendChat(listOf(mapOf("role" to "user", "content" to text)))
            // parse simple response (depends on provider)
            resp.choices?.firstOrNull()?.get("message")?.toString() ?: "(no response)"
        } catch (e: Exception) {
            // fallback for demo
            delay(500)
            "Ответ от ассистента (демо): " + text.reversed().take(50)
        }
    }
}
