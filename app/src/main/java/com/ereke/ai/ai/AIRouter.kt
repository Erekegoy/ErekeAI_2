package com.ereke.ai.ai

enum class AIProvider {
    GROQ,
    GEMINI,
    OPENAI,
    OFFLINE
}

object AIRouter {

    var provider = AIProvider.GROQ

    fun chat(prompt: String): String {
        provider = com.ereke.ai.settings.SettingsManager.getProvider()
        return when (provider) {
            AIProvider.GROQ -> GroqClient.chat(prompt)
            AIProvider.GEMINI -> GeminiClient.chat(prompt)
            AIProvider.OPENAI -> OpenAIClient.chat(prompt)
            AIProvider.OFFLINE -> OfflineClient.chat(prompt)
        }
    }
}
