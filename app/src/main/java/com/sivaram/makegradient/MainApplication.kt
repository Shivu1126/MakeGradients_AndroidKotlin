package com.sivaram.makegradient

import android.app.Application
import androidx.room.Room
import com.sivaram.makegradient.ui.database.GradientDatabase

class MainApplication: Application() {
    companion object{
        lateinit var gradientDatabase: GradientDatabase
    }
    override fun onCreate() {
        super.onCreate()
        gradientDatabase = Room.databaseBuilder(
            applicationContext,
            GradientDatabase::class.java,
            GradientDatabase.DATABASE_NAME
        ).build()
    }
}