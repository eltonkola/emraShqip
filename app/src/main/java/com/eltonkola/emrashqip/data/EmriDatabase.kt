package com.eltonkola.emrashqip.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Emri::class],
    version = 1,
    exportSchema = false
)
abstract class EmriDatabase : RoomDatabase() {
    abstract fun emriDao(): EmriDao
}
