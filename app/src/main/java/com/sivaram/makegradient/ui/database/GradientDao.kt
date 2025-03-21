package com.sivaram.makegradient.ui.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.sivaram.makegradient.ui.datamodel.Gradient

@Dao
interface GradientDao {
    @Query("SELECT * FROM gradient")
    fun getAllGradient(): LiveData<List<Gradient>>

    @Query("SELECT * FROM gradient WHERE isFavorite = 1")
    fun getFavoriteGradient(): LiveData<List<Gradient>>

    @Upsert
    fun addGradient(gradient: Gradient)

    @Delete
    fun deleteGradient(gradient: Gradient)

    @Query("UPDATE gradient SET isFavorite = :isFavorite WHERE id = :id")
    fun changeFavoriteStatus(id: Int, isFavorite: Boolean)
}