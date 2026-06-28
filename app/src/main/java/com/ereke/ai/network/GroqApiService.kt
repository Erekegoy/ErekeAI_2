package com.ereke.ai.network

import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

// Simple models
data class ChatResponse(@SerializedName("choices") val choices: List<Map<String, Any>>?)

interface GroqApi {
    @POST
    suspend fun chat(@Url url: String, @Body body: Any): ChatResponse
}

object GroqApiService {
    private const val BASE = "https://api.groq.com/"
    private val apiKey = com.ereke.ai.BuildConfig.GROQ_API_KEY

    private val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val req = chain.request().newBuilder().addHeader("Authorization", "Bearer $apiKey").build()
            chain.proceed(req)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val svc = retrofit.create(GroqApi::class.java)

    // Simplified: caller provides endpoint path relative to base
    suspend fun sendChatRequest(path: String, body: Any): ChatResponse = svc.chat(path, body)

    // Convenience wrapper for existing demo repo usage
    fun create(): GroqApiWrapper = GroqApiWrapper(svc)
}

class GroqApiWrapper(private val svc: GroqApi) {
    suspend fun sendChat(messages: List<Map<String, String>>): ChatResponse {
        val body = mapOf("model" to "llama-3.1", "messages" to messages)
        return svc.chat("v1/chat/completions", body)
    }
}
