package com.ereke.ai.ui

import coil.compose.AsyncImage
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import com.ereke.ai.tts.TTSManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.ereke.ai.data.ChatViewModel
import com.ereke.ai.data.Message
import com.ereke.ai.ui.screens.ChatScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErekeApp() {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        TTSManager.init(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ErekeAI") }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            ChatScreen()
        }
    }
} {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(messages) { msg ->
                    MessageBubble(msg)
                }
            }
selectedImage?.let { uri ->
    AsyncImage(
        model = uri,
        contentDescription = "Selected image",
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
    )
}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f)
                )
            IconButton(
    onClick = {
        // TODO: запуск голосового ввода
    }
) {
    Icon(
        imageVector = Icons.Default.Mic,
        contentDescription = "Voice"
    )
}

IconButton(
    onClick = {
        imagePicker.launch("image/*")
    }
) {
    Icon(
        imageVector = Icons.Default.AttachFile,
        contentDescription = "Attach"
    )
}

Button(
    onClick = {
        if (input.isNotBlank()) {
            vm.sendUserMessage(input)
            input = ""
        }
    }
) {
    Text("Send")
}

            }
        }
    }
}

@Composable
fun MessageBubble(msg: Message) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = msg.text,
            modifier = Modifier.padding(12.dp)
        )
    }
}
