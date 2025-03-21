package com.sivaram.makegradient.ui.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gradient")
data class Gradient(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val prefixColor: String,
    val suffixColor: String,
    val isFavorite: Boolean
) {

}