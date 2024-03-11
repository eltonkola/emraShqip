package com.eltonkola.emrashqip.ui

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.eltonkola.emrashqip.ui.MainAppViewModel

@Composable
fun SettingsScreen( viewModel: MainAppViewModel) {

    val emrat = viewModel.lastName.collectAsState()

   // var text by rememberSaveable { mutableStateOf("Text") }

    TextField(
        value = emrat.value,
        onValueChange = {
            viewModel.setlastName(it)
        },
        label = { Text("Last name of the baby") }
    )


}
