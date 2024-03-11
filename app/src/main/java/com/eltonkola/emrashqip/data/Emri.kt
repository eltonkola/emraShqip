package com.eltonkola.emrashqip.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Emrat")
data class Emri(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val emri: String,
    val popularity: Int,
    val sex : Int,
    val favorite : Int
)