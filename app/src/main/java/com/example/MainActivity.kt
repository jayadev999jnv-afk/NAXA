package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.SettingsRepository
import com.example.ui.SettingsViewModel
import com.example.ui.SettingsViewModelFactory
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()
    val repository = SettingsRepository(db.ttsSettingsDao())
    val viewModel = ViewModelProvider(this, SettingsViewModelFactory(repository))[SettingsViewModel::class.java]

    setContent {
      MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          TtsSettingsScreen(viewModel, modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun TtsSettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
  val speed by viewModel.ttsSpeed.collectAsState()
  val apiKey by viewModel.apiKey.collectAsState()

  Column(modifier = modifier.padding(16.dp)) {
    Text(text = "TTS Playback Speed: ${"%.2f".format(speed)}x", style = MaterialTheme.typography.titleMedium)
    Slider(
      value = speed,
      onValueChange = { viewModel.updateTtsSpeed(it) },
      valueRange = 0.5f..2.0f
    )
    
    androidx.compose.material3.OutlinedTextField(
      value = apiKey,
      onValueChange = { viewModel.updateApiKey(it) },
      label = { Text("Gemini API Key") },
      modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
    )
  }
}
