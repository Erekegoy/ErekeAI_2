package com.ereke.ai.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class GroqWebSocket {
    private val apiKey = com.ereke.ai.BuildConfig.GROQ_API_KEY
    private val client = OkHttpClient()

    fun open(url: String, listener: WebSocketListener): WebSocket {
        val req = Request.Builder().url(url).addHeader("Authorization", "Bearer $apiKey").build()
        return client.newWebSocket(req, listener)
    }
}
