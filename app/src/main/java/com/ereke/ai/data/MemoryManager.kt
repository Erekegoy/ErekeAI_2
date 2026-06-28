package com.ereke.ai.data

object MemoryManager {

    private val history = mutableListOf<Message>()

    fun add(message: Message) {
        history.add(message)
    }

    fun getHistory(): List<Message> {
        return history.toList()
    }

    fun clear() {
        history.clear()
    }
}
