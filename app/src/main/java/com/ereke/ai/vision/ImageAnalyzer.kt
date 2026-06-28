package com.ereke.ai.vision

import android.net.Uri

object ImageAnalyzer {

    suspend fun analyze(image: Uri): String {
        return "🖼️ Анализ изображения скоро будет доступен.\n\nURI: $image"
    }

}
