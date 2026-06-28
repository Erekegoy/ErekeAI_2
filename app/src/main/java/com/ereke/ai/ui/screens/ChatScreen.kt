package com.ereke.ai.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ereke.ai.data.ChatViewModel
import com.ereke.ai.data.Message
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

@Composable
fun ChatScreen() {

    val vm = remember { ChatViewModel() }
    val messages by vm.messages.collectAsState()

    var input by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImage = uri
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages) {
                MessageBubble(it)
            }
        }

        selectedImage?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
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
    modifier = Modifier.weight(1f),
    singleLine = true,
    keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Send
    ),
    keyboardActions = KeyboardActions(
        onSend = {
            if (input.isNotBlank()) {
                vm.sendUserMessage(input)
                input = ""
            }
        }
    )
)

            IconButton(
                onClick = {
                    imagePicker.launch("image/*")
                }
            ) {
                Icon(Icons.Default.AttachFile, null)
            }

            IconButton(
                onClick = {
                    // Voice v1
                }
            ) {
                Icon(Icons.Default.Mic, null)
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

@Composable
private fun MessageBubble(msg: Message) {
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
