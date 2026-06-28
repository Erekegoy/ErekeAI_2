package com.ereke.ai.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repo = ChatRepository()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    init {
        _messages.value = MemoryManager.getHistory()
    }

    fun sendUserMessage(text: String) {

        val user = Message(text, true)
        MemoryManager.add(user)
        com.ereke.ai.memory.ConversationMemory.add(user)
        _messages.value = MemoryManager.getHistory()

        val loading = Message("🤖 ErekeAI думает...", false)
        MemoryManager.add(loading)
        _messages.value = MemoryManager.getHistory()

        viewModelScope.launch {

            val answer = repo.ask(text)
            
            com.ereke.ai.tts.TTSManager.speak(answer)

            MemoryManager.clear()

            MemoryManager.add(user)
        com.ereke.ai.memory.ConversationMemory.add(user)
            MemoryManager.add(Message(answer, false))

            _messages.value = MemoryManager.getHistory()
        }
    }
}
