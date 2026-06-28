package com.ereke.ai.ai

import com.ereke.ai.BuildConfig
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object GroqClient {

    private val client = OkHttpClient()

    fun chat(prompt: String): String {

        val json = """
        {
          "model":"llama-3.3-70b-versatile",
          "messages":[
            {
              "role":"user",
              "content":"$prompt"
            }
          ]
        }
        """.trimIndent()

        val request = Request.Builder()
            .url("https://api.groq.com/openai/v1/chat/completions")
            .addHeader("Authorization","Bearer ${BuildConfig.GROQ_API_KEY}")
            .addHeader("Content-Type","application/json")
            .post(json.toRequestBody("application/json".toMediaType()))
            .build()

        return client.newCall(request).execute().body?.string() ?: ""
    }
}
