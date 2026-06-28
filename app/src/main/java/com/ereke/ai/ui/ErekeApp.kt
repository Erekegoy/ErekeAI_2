package com.ereke.ai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ereke.ai.data.Message
import com.ereke.ai.data.ChatViewModel

@Composable
fun ErekeApp() {
    val vm = remember { ChatViewModel() }
    val messages by vm.messages.collectAsState()
    var input by remember { mutableStateOf("") }

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Ereke AI Assistant") }) },
            content = { padding ->
                Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                    LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(), reverseLayout = false) {
                        items(messages) { msg ->
                            MessageBubble(msg)
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = input,
                            onValueChange = { input = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Type a message...") }
                        )
                        IconButton(onClick = {
                            if (input.isNotBlank()) {
                                vm.sendUserMessage(input)
                                input = ""
                            }
                        }) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                        IconButton(onClick = {
                            // placeholder for mic / voice input
                            vm.appendAssistantMessage("[Voice input placeholder]")
                        }) {
                            Icon(Icons.Default.Mic, contentDescription = "Mic")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun MessageBubble(msg: Message) {
    val isUser = msg.isUser
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start) {
        Column(modifier = Modifier.widthIn(max = 280.dp).background(if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface).padding(12.dp)) {
            Text(if (isUser) "You" else "Assistant", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Spacer(Modifier.height(6.dp))
            Text(msg.text)
        }
    }
}
