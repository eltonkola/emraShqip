package com.eltonkola.emrashqip.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmriDao {
    @Query("SELECT * FROM Emrat limit 100")
    suspend fun getAllEmri(): List<Emri>
}