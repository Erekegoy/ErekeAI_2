package com.ereke.ai.ai

import okhttp3.OkHttpClient
import java.time.Duration

object NetworkConfig {

    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(30))
            .readTimeout(Duration.ofSeconds(60))
            .writeTimeout(Duration.ofSeconds(60))
            .callTimeout(Duration.ofSeconds(90))
            .retryOnConnectionFailure(true)
            .build()
    }
}
