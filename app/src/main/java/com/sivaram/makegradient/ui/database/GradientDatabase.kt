package com.sivaram.makegradient.ui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sivaram.makegradient.ui.datamodel.Gradient

@Database(entities = [Gradient::class], version = 1)
abstract class GradientDatabase: RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "gradient_database"
    }

    abstract fun getGradientDao(): GradientDao
}