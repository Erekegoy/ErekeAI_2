package com.ereke.ai.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.concurrent.thread

class ChatViewModel {

    private val repo = ChatRepository()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    init {
        _messages.value = MemoryManager.getHistory()
    }

    fun sendUserMessage(text: String) {

        val user = Message(text, true)
        MemoryManager.add(user)

        _messages.value = MemoryManager.getHistory()

        thread {

            val answer = repo.ask(text)

            val ai = Message(answer, false)
            MemoryManager.add(ai)

            _messages.value = MemoryManager.getHistory()
        }
    }
}
