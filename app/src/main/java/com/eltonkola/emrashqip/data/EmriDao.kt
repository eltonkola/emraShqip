package com.eltonkola.emrashqip.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EmriDao {


    @Query("SELECT * FROM Emrat where favorite == -1 order by popularity desc")
    fun loadUnvotedNamed(): Flow<List<Emri>>

    @Query("SELECT * FROM Emrat where favorite > 0 order by popularity desc")
    fun getFavorite(): Flow<List<Emri>>

    @Query("SELECT * FROM Emrat order by popularity desc")
    fun getAllData(): Flow<List<Emri>>

    @Query("SELECT * FROM Emrat where sex == -1 order by popularity desc")
    fun getAllWithoutGender(): Flow<List<Emri>>

    @Update(entity = Emri::class)
    suspend fun updateEmri(emri: Emri)

}

const val MALE = 0
const val FEMALE = 1
const val DONTLIKE = 0
const val LIKE = 1
