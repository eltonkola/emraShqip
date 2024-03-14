package com.eltonkola.emrashqip.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.eltonkola.emrashqip.ui.MainAppViewModel

@Composable
fun SettingsScreen( viewModel: MainAppViewModel) {

    val emrat = viewModel.lastName.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = emrat.value,
            onValueChange = {
                viewModel.setlastName(it)
            },
            label = { Text("Last name of the baby") }
        )
    }
}
