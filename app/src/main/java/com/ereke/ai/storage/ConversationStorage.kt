package com.ereke.ai.storage

import com.ereke.ai.data.Message

object ConversationStorage {

    private val messages = mutableListOf<Message>()

    fun save(message: Message) {
        messages.add(message)
    }

    fun load(): List<Message> {
        return messages.toList()
    }

    fun clear() {
        messages.clear()
    }
}
