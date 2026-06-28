package com.ereke.ai.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.concurrent.thread

class ChatViewModel {

    private val repo = ChatRepository()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun sendUserMessage(text: String) {
        _messages.value = _messages.value + Message(text, true)

        thread {
            val answer = repo.ask(text)
            _messages.value = _messages.value + Message(answer, false)
        }
    }
}
