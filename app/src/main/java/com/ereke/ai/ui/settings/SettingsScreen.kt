package com.ereke.ai.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ereke.ai.ai.AIProvider
import com.ereke.ai.settings.SettingsManager

@Composable
fun SettingsScreen() {

    var selected by remember {
        mutableStateOf(SettingsManager.getProvider())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "AI Provider",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        AIProvider.entries.forEach { provider ->

            Row {

                RadioButton(
                    selected = selected == provider,
                    onClick = {
                        selected = provider
                        SettingsManager.setProvider(provider)
                    }
                )

                Text(
                    provider.name,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}
