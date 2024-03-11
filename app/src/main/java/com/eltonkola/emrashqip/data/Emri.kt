package com.eltonkola.emrashqip.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Emrat")
data class Emri(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "emri", typeAffinity = ColumnInfo.TEXT)
    val emri: String,
    @ColumnInfo(name = "frequency", typeAffinity = ColumnInfo.INTEGER)
    val frequency: Int,
    @ColumnInfo(name = "isMale", typeAffinity = ColumnInfo.INTEGER)
    val isMale : Int,
    @ColumnInfo(name = "liked", typeAffinity = ColumnInfo.INTEGER)
    val liked : Int
)