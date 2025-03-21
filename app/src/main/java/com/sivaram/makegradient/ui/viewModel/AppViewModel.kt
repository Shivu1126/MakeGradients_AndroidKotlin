package com.sivaram.makegradient.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sivaram.makegradient.MainApplication
import com.sivaram.makegradient.ui.datamodel.Gradient
import com.sivaram.makegradient.ui.manager.GradientRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

   private val gradientDao = MainApplication.gradientDatabase.getGradientDao()
   val gradientList: LiveData<List<Gradient>> = gradientDao.getAllGradient()
   val favoriteGradientList: LiveData<List<Gradient>> = gradientDao.getFavoriteGradient()

   var editableGradient: Gradient? by mutableStateOf(null)
   var searchText = mutableStateOf("")

   val filteredGradientList = MediatorLiveData<List<Gradient>>().apply {
      addSource(gradientList) { searchByName() }
   }

   fun searchByName(){
         filteredGradientList.value = gradientList.value?.filter {
            it.name.lowercase().contains(searchText.value.lowercase())
         }?: emptyList()
   }

   fun addGradient(id: Int, name: String, prefixColor: String, suffixColor: String, isFavorite: Boolean){
      viewModelScope.launch(Dispatchers.IO) {
         gradientDao.addGradient(Gradient(id, name, prefixColor, suffixColor, isFavorite))
      }
   }

   fun changeFavoriteStatus(id: Int, isFavorite: Boolean){
      viewModelScope.launch(Dispatchers.IO) {
         gradientDao.changeFavoriteStatus(id, isFavorite)
      }
   }
   fun deleteGradient(gradient: Gradient){
      viewModelScope.launch(Dispatchers.IO) {
         gradientDao.deleteGradient(gradient)
      }
   }
}