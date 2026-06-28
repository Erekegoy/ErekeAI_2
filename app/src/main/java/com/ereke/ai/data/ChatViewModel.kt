package com.ereke.ai.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(listOf(Message("Привет! Я Ereke AI.", false)))
    val messages = _messages.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val repo = ChatRepository()

    fun sendUserMessage(text: String) {
        val userMsg = Message(text, true)
        _messages.value = _messages.value + userMsg
        // send to repo and get assistant response
        scope.launch {
            val reply = repo.sendMessage(text)
            _messages.value = _messages.value + Message(reply, false)
        }
    }

    fun appendAssistantMessage(text: String) {
        _messages.value = _messages.value + Message(text, false)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
