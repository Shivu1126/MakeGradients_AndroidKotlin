package com.sivaram.makegradient.ui.view
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sivaram.makegradient.R
import com.sivaram.makegradient.ui.constantsVal.AppConstants
import com.sivaram.makegradient.ui.datamodel.Gradient
import com.sivaram.makegradient.ui.viewModel.AppViewModel

@Composable
fun HomePage(navController: NavHostController, viewModel: AppViewModel) {
    val systemUiController = rememberSystemUiController()

    // Set status bar color
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.LightGray,
            darkIcons = true
        )
        systemUiController.setNavigationBarColor(
            color = Color.LightGray,
            darkIcons = true
        )
    }
    val list by viewModel.filteredGradientList.observeAsState(emptyList())
    Scaffold (modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppConstants.GRADIENT_MANAGER_ROUTE)
            },
                containerColor = Color.White,
                contentColor = Color.Black

            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Icon")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
        )
        { innerPadding ->

            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize())
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray)
                        .padding(horizontal = 15.dp)
                        .padding(top = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .align(alignment = Alignment.CenterVertically)
                        ) {
                            var searchText by rememberSaveable { mutableStateOf("") }
                            var isVisible by rememberSaveable { mutableStateOf(true) }
                            BasicTextField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                    viewModel.searchText.value = searchText
                                    viewModel.searchByName()
                                },
                                maxLines = 1,
                                singleLine = true,
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .padding(start = 25.dp, end = 30.dp)
                                    ) {
                                        if (searchText.isEmpty()) {
                                            isVisible = false
                                            Text(
                                                text = "Search gradient...",
                                                color = Color.Gray,
                                                maxLines = 1
                                            )
                                        } else {
                                            isVisible = true
                                        }
                                        innerTextField()
                                    }
                                },
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(35.dp)
                                    .background(
                                        colorResource(id = R.color.white),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        0.5.dp,
                                        colorResource(id = R.color.black),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "Search Icon",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(30.dp)
                                    .align(Alignment.CenterStart)
                                    .padding(start = 10.dp)
                            )
                            if (isVisible) {
                                IconButton(
                                    onClick = {
                                        searchText = ""
                                        viewModel.searchText.value = searchText
                                        viewModel.searchByName()
                                    },
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.CenterEnd)
                                        .padding(end = 10.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.Clear,
                                        contentDescription = "Clear Icon",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }

                        IconButton(onClick = {
                            navController.navigate(AppConstants.FAVOURITE_ROUTE)
                        }) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorite Icon",
                                tint = Color.Red,
                                modifier = Modifier
                                    .size(35.dp)
                            )
                        }
                    }

                    list?.let {
                        GradientLazyColumn(gradients = it, viewModel, navController,"Home")
                    }?: EmptyGradientList("Home")
                }

            }

        }
}
@Composable
fun EmptyGradientList(navFrom: String){
    var label = "Create Your First Gradient"
    if(navFrom.equals("Favourite"))
        label = "Make Favourite Gradient"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.empty_icon),
            contentDescription = "Empty Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(200.dp)
                .padding(start = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun GradientItem(
    gradient: Gradient,
    navController: NavHostController,
    viewModel: AppViewModel
){
   val id: Int = gradient.id
   val name: String = gradient.name
   val prefixColor: String = gradient.prefixColor
   val suffixColor: String = gradient.suffixColor
   val isFavorite: Boolean = gradient.isFavorite
    val clipboardManager = LocalClipboardManager.current
    val brush = Brush.horizontalGradient(
        listOf(
            Color(android.graphics.Color.parseColor(prefixColor)),
            Color(android.graphics.Color.parseColor(suffixColor))
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(horizontal = 0.dp, vertical = 10.dp),
        border = BorderStroke(2.dp,Color.Black),
        onClick = {
            viewModel.editableGradient = gradient
            navController.navigate(AppConstants.GRADIENT_MANAGER_ROUTE)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ){
                Text(
                    textAlign = TextAlign.Center,
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.TopStart)
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {
                        viewModel.deleteGradient(gradient)

                    },
                            modifier = Modifier.size(35.dp)
                        ) {
                        Icon(
                            imageVector = (Icons.Filled.Delete),
                            contentDescription = "copy_gradient_name",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.changeFavoriteStatus(id, !isFavorite)
                        },
                        modifier = Modifier
                            .size(30.dp),
                        
                    ) {
                        if(isFavorite){
                            Icon(
                                painterResource(id = R.drawable.favrt_icon),
                                contentDescription = "copy_gradient_name",
                                tint = Color.Red
                            )
                        }
                        else{
                            Icon(
                                painterResource(id = R.drawable.unfavrt_icon),
                                contentDescription = "favorite_gradient",
                                tint = Color.Black
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.BottomEnd)
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            textAlign = TextAlign.Center,
                            text = prefixColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )
                        IconButton(
                            onClick = {
                                clipboardManager.setText((AnnotatedString(prefixColor)))
                            },
                            modifier = Modifier
                                .size(25.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.copy_icon),
                                contentDescription = "copy_prefix_color_code",
                                tint = Color.Black
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                                onClick = {
                                    clipboardManager.setText((AnnotatedString(suffixColor)))
                                },
                                modifier = Modifier
                                    .size(25.dp)
                            ) {
                            Icon(
                                painterResource(id = R.drawable.copy_icon),
                                contentDescription = "copy_suffix_color_code",
                                tint = Color.Black
                            )
                        }
                        Text(
                            textAlign = TextAlign.Center,
                            text = suffixColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GradientLazyColumn(
    gradients: List<Gradient>,
    viewModel: AppViewModel,
    navController: NavHostController,
    navFrom: String,
    modifier: Modifier = Modifier
){
    if(gradients.isEmpty()){
        EmptyGradientList(navFrom)
    }
    else {
        LazyColumn(modifier) {

            gradients.forEach { gradient ->
                item {
                    GradientItem(
                        gradient,
                        navController,
                        viewModel
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreviewLight(){
    val navController = rememberNavController()
    val viewModel = AppViewModel()
    HomePage(navController, viewModel)
}
@Preview(showBackground = true)
@Composable
fun GradientPreview(){
    GradientItem(Gradient(id = 1, name = "Gradient 1", prefixColor = "#FF0000", suffixColor = "#00FF00", isFavorite = true), viewModel = AppViewModel(), navController = rememberNavController())
}