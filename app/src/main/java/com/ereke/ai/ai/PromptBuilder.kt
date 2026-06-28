package com.ereke.ai.ai

import com.ereke.ai.core.SystemPrompt
import com.ereke.ai.memory.ConversationMemory

object PromptBuilder {

    fun build(userMessage: String): List<ChatMessage> {

        val list = mutableListOf<ChatMessage>()

        list.add(
            ChatMessage(
                "system",
                SystemPrompt.TEXT
            )
        )

        ConversationMemory.last(10).forEach {

            list.add(
                ChatMessage(
                    if (it.isUser) "user" else "assistant",
                    it.text
                )
            )
        }

        list.add(
            ChatMessage(
                "user",
                userMessage
            )
        )

        return list
    }
}
