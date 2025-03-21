package com.sivaram.makegradient.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sivaram.makegradient.ui.viewModel.AppViewModel

@Composable
fun FavouritePage(navController: NavHostController, viewModel: AppViewModel){
    val favoriteGradientList by viewModel.favoriteGradientList.observeAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
        .padding(15.dp),
        verticalArrangement = Arrangement.Top
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back",
                tint = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .size(30.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "Favourite Gradients",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
        }

        favoriteGradientList?.let {
            GradientLazyColumn(gradients = it, viewModel = viewModel, navController, navFrom = "Favourite")
        }?: EmptyGradientList(navFrom = "Favourite")
    }
}
@Preview
@Composable
fun FavouritePagePreview(){
    FavouritePage(navController = rememberNavController(), viewModel = AppViewModel())
}