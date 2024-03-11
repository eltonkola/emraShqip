package com.eltonkola.emrashqip.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eltonkola.emrashqip.data.Emri
import com.eltonkola.emrashqip.data.FEMALE
import com.eltonkola.emrashqip.data.MALE
import com.eltonkola.emrashqip.ui.MainAppViewModel
import kotlinx.coroutines.launch

@Composable
fun NamesScreen( viewModel: MainAppViewModel) {
    Column {

        val scope = rememberCoroutineScope()


        val emrat = remember { viewModel.allEmrat }.collectAsState()

        var gjithsej = remember { mutableStateOf(0) }
        var aktual = remember { mutableStateOf(0) }

        var progress by remember {  mutableStateOf(0.0f) }

        LaunchedEffect(key1 = emrat) {
            gjithsej.value = emrat.value.size
            aktual.value = emrat.value.filter { it.sex == -1 }.size
            println("gjithsej ${gjithsej.value} - aktual ${aktual.value}")
            progress = 1.0f - calculatePercentage(gjithsej.value, aktual.value)
        }


        Text(text = "Incomplete: ${aktual.value} / ${gjithsej.value} - %${progress}")

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = progress,
        )

        LazyColumn {
            items(
                items = emrat.value,
                //   key =  Emri::id
            ) { emri ->
                OptionRow(
                    emri = emri,
                    onFemaleClick = {
                        scope.launch {
                            viewModel.updateEmri(emri.copy(sex = FEMALE))
                        }
                    },
                    onMaleClick = {
                        scope.launch {
                            viewModel.updateEmri(emri.copy(sex = MALE))
                        }
                    })

            }

        }

    }
}


fun calculatePercentage(total: Int, completion: Int): Float {

    if (total == 0) {
        return 0.0f  // Avoid division by zero
    }
    return completion.toFloat() / total.toFloat()
}


@Composable
fun OptionRow(
    emri: Emri,
    onFemaleClick: () -> Unit = {},
    onMaleClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth()) {
            Text(text = emri.emri)

            Text(text = emri.popularity.toString())

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier,
                onClick = onFemaleClick,
                colors = ButtonDefaults.buttonColors(containerColor = if(emri.sex == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Female")
            }

            Button(
                modifier = Modifier,
                onClick = onMaleClick,
                colors = ButtonDefaults.buttonColors(containerColor = if(emri.sex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary)
            ) {
                Text(text = "Male")
            }

        }
    }

}