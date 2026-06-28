package com.ereke.ai.ai

object JsonMessageBuilder {

    fun build(messages: List<ChatMessage>): String {

        return messages.joinToString(
            prefix = "[",
            postfix = "]"
        ) {
            """{"role":"${it.role}","content":"${it.content.replace("\"","\\\"")}"}"""
        }
    }
}
