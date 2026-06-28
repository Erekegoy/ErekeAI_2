package com.ereke.ai.ai

enum class AIProvider {
    GROQ
}

object AIRouter {

    private var provider = AIProvider.GROQ

    fun setProvider(ai: AIProvider) {
        provider = ai
    }

    fun chat(prompt: String): String {
        return when (provider) {
            AIProvider.GROQ -> GroqClient.chat(prompt)
        }
    }
}
