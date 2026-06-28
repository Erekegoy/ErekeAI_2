package com.ereke.ai.ai

import com.ereke.ai.core.SystemPrompt
import com.ereke.ai.memory.ConversationMemory

object PromptBuilder {

    fun build(userMessage: String): List<ChatMessage> {

        val messages = mutableListOf<ChatMessage>()

        messages.add(
            ChatMessage(
                "system",
                SystemPrompt.TEXT
            )
        )

        ConversationMemory.last(10).forEach {
            messages.add(
                ChatMessage(
                    if (it.isUser) "user" else "assistant",
                    it.text
                )
            )
        }

        messages.add(ChatMessage("user", userMessage))

        return messages
    }
}
