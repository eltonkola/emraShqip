package com.eltonkola.emrashqip

import android.app.Application
import androidx.room.Room
import com.eltonkola.emrashqip.data.EmriDatabase

class MainApp: Application() {

    companion object {
        lateinit var database: EmriDatabase
    }


    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            EmriDatabase::class.java, "emrat-database"
        ).createFromAsset("emrat.db")
            .build()

    }
}