package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TtsSettingsDao {
    @Query("SELECT * FROM TtsSettings WHERE id = 0")
    fun getSettings(): Flow<TtsSettings?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSettings(settings: TtsSettings)
}
