package com.eltonkola.emrashqip

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.eltonkola.emrashqip.ui.MainAppViewModel
import com.eltonkola.emrashqip.ui.theme.EmraShqipTheme
import java.util.Locale
import android.Manifest
import android.content.pm.PackageManager

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Set language (English in this case)
                val result = textToSpeech?.setLanguage(Locale.US) // Or Locale.ENGLISH
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Language data is missing or not supported, handle appropriately
                    // (e.g., prompt user to install language pack)
                } else {
                    // Engine ready for use
                }
            } else {
                // Initialization failed, handle error
            }
        }
        requestRecordAudioPermission()


        viewModel = ViewModelProvider(this)[MainAppViewModel::class.java]
         setContent {
             EmraShqipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MainAppScreen(viewModel, navController, this::speakText)
                }
            }
        }
    }

    private fun speakText(text: String) {
        textToSpeech?.let {
            it.speak(text, TextToSpeech.QUEUE_ADD, null, null) // Add to speech queue
        }
    }

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 100

    private fun requestRecordAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.let {
            it.stop()
            it.shutdown()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with TTS setup
            } else {
                // Permission denied, handle accordingly (e.g., show an explanation)
            }
        }
    }
    private var textToSpeech: TextToSpeech? = null


}
