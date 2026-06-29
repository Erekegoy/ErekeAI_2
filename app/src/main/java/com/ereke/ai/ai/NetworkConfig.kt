package com.ereke.ai.ai

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration
import java.util.concurrent.TimeUnit

object NetworkConfig {

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()

            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)

            .retryOnConnectionFailure(true)

            .connectionPool(
                ConnectionPool(
                    5,
                    5,
                    TimeUnit.MINUTES
                )
            )

            .addInterceptor(logger)

            .build()
    }
}
