package com.ereke.ai.ai

object AIManager {

    suspend fun reply(message: String): String {
        return AIRouter.chat(message)
    }

}
