package com.sivaram.makegradient.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sivaram.makegradient.ui.constantsVal.AppConstants
import com.sivaram.makegradient.ui.view.FavouritePage
import com.sivaram.makegradient.ui.view.GradientManager
import com.sivaram.makegradient.ui.view.HomePage
import com.sivaram.makegradient.ui.viewModel.AppViewModel

@Composable
fun Nav(){
    val navController = rememberNavController()
    val viewModel : AppViewModel = viewModel()
    NavHost(navController = navController, startDestination = AppConstants.HOME_ROUTE){
        composable(AppConstants.HOME_ROUTE){ HomePage(navController, viewModel) }
        composable(AppConstants.GRADIENT_MANAGER_ROUTE){ GradientManager(navController, viewModel) }
        composable(AppConstants.FAVOURITE_ROUTE){ FavouritePage(navController, viewModel) }
    }
}