package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TtsSettings(@PrimaryKey val id: Int = 0, val speed: Float, val apiKey: String = "")
