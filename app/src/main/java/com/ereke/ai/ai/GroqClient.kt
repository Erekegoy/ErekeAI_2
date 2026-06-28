package com.ereke.ai.ai

import com.google.gson.JsonParser
import com.ereke.ai.BuildConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object GroqClient {

    private val client = OkHttpClient.Builder().callTimeout(java.time.Duration.ofSeconds(60)).build()

    suspend fun chat(prompt: String): String {

        return try {

            val json = """
            {
              "model":"llama-3.3-70b-versatile","temperature":0.7,"max_tokens":1024,
              "messages":[{"role":"system","content":"Ты — ErekeAI, персональный ИИ-помощник Ерлана."},
                {
                  "role":"user",
                  "content":"$prompt"
                }
              ]
            }
            """.trimIndent()

            val request = Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")
                .addHeader("Authorization", "Bearer ${BuildConfig.GROQ_API_KEY}")
                .addHeader("Content-Type", "application/json")
                .post(json.toRequestBody("application/json".toMediaType()))
                .build()

            val body = client.newCall(request).execute().body?.string() ?: return "Empty response"

            val root = JsonParser().parse(body).asJsonObject

            root.getAsJsonArray("choices")
                .get(0)
                .asJsonObject
                .getAsJsonObject("message")
                .get("content")
                .asString

        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
