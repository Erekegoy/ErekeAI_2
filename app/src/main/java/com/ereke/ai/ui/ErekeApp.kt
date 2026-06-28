package com.ereke.ai.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ereke.ai.tts.TTSManager
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
                title = {
                    Text("ErekeAI")
                }
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
}
