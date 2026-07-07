package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first

class SettingsRepository(private val ttsSettingsDao: TtsSettingsDao) {
    val ttsSpeed: Flow<Float> = ttsSettingsDao.getSettings().map { it?.speed ?: 1.0f }
    val apiKey: Flow<String> = ttsSettingsDao.getSettings().map { it?.apiKey ?: "" }

    suspend fun updateTtsSpeed(speed: Float) {
        val currentSettings = ttsSettingsDao.getSettings().first()
        ttsSettingsDao.updateSettings(TtsSettings(speed = speed, apiKey = currentSettings?.apiKey ?: ""))
    }

    suspend fun updateApiKey(apiKey: String) {
        val currentSettings = ttsSettingsDao.getSettings().first()
        ttsSettingsDao.updateSettings(TtsSettings(speed = currentSettings?.speed ?: 1.0f, apiKey = apiKey))
    }
}
