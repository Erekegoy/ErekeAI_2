package com.ereke.ai.memory

import com.ereke.ai.data.Message

object ConversationMemory {

    private val history = mutableListOf<Message>()

    fun add(message: Message) {
        history.add(message)
    }

    fun all(): List<Message> {
        return history.toList()
    }

    fun last(count: Int): List<Message> {
        return history.takeLast(count)
    }

    fun clear() {
        history.clear()
    }
}
