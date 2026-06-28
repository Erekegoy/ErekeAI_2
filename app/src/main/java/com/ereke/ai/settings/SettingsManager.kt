package com.ereke.ai.settings

import com.ereke.ai.ai.AIProvider

object SettingsManager {

    private var currentProvider = AIProvider.GROQ

    fun getProvider(): AIProvider {
        return currentProvider
    }

    fun setProvider(provider: AIProvider) {
        currentProvider = provider
    }
}
