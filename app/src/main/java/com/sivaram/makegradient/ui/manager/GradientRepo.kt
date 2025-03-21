package com.sivaram.makegradient.ui.manager

import com.sivaram.makegradient.ui.datamodel.Gradient

object GradientRepo {
    private var gradientList = mutableListOf<Gradient>()


    fun getGradientList(): List<Gradient> {
        return gradientList;
    }
    fun addGradient(id: Int, name: String, prefixColor: String, suffixColor: String, isFavorite: Boolean){
        gradientList.add(Gradient(
            id = id,
            name = name,
            prefixColor = prefixColor,
            suffixColor = suffixColor,
            isFavorite = isFavorite
        ))
    }

    fun changeFavoriteStatus(gradient: Gradient, isFavorite: Boolean){
//        gradient.isFavorite = !isFavorite
    }
    fun deleteGradient(id: Int){
        gradientList.removeIf { it.id == id }
    }
}