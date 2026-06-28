package com.ereke.ai.ai

import com.ereke.ai.BuildConfig
import com.google.gson.JsonParser
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object GroqClient {

    private val client = NetworkConfig.client

    suspend fun chat(prompt: String): String {
        return try {

            val messages = PromptBuilder.build(prompt)

            val json = """
            {
              "model":"llama-3.3-70b-versatile",
              "temperature":0.5,
              "max_tokens":1024,
              "messages":${JsonMessageBuilder.build(messages)}
            }
            """.trimIndent()

            if (BuildConfig.GROQ_API_KEY == "YOUR_KEY_HERE" ||
                BuildConfig.GROQ_API_KEY == "PUT_YOUR_GROQ_API_KEY_HERE" ||
                BuildConfig.GROQ_API_KEY.isBlank()
            ) {
                return "❌ API KEY не загружен: ${BuildConfig.GROQ_API_KEY}"
            }

            val request = Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")
                .addHeader("Authorization", "Bearer ${BuildConfig.GROQ_API_KEY}")
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "ErekeAI/1.0")
                .post(json.toRequestBody("application/json".toMediaType()))
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                return "HTTP ${response.code}: $body"
            }

            JsonParser.parseString(body)
                .asJsonObject
                .getAsJsonArray("choices")
                .get(0)
                .asJsonObject
                .getAsJsonObject("message")
                .get("content")
                .asString

        } catch (e: Exception) {
            e.printStackTrace()
            return "⚠️ ${e.javaClass.simpleName}: ${e.message ?: "без сообщения"}"
        }
    }
}
