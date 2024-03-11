package com.eltonkola.emrashqip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.eltonkola.emrashqip.ui.MainAppViewModel
import com.eltonkola.emrashqip.ui.theme.EmraShqipTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainAppViewModel::class.java]

        setContent {
            EmraShqipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainAppScreen(viewModel)
                }
            }
        }
    }
}
