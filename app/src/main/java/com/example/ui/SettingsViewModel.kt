package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    val ttsSpeed = settingsRepository.ttsSpeed.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1.0f)
    val apiKey = settingsRepository.apiKey.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    fun updateTtsSpeed(speed: Float) {
        viewModelScope.launch {
            settingsRepository.updateTtsSpeed(speed)
        }
    }

    fun updateApiKey(apiKey: String) {
        viewModelScope.launch {
            settingsRepository.updateApiKey(apiKey)
        }
    }
}
