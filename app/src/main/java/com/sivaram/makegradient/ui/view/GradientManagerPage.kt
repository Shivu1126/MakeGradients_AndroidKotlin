package com.sivaram.makegradient.ui.view

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sivaram.makegradient.R
import com.sivaram.makegradient.ui.viewModel.AppViewModel

@Composable
fun GradientManager(navController: NavHostController, appViewModel: AppViewModel) {
//    val viewModel by appViewModel.gradientList.observeAsState()
    var prefixColorCode by rememberSaveable { mutableStateOf("#FFFFFF") }
    var suffixColorCode by rememberSaveable { mutableStateOf("#FFFFFF") }
    var gradientName by rememberSaveable { mutableStateOf("") }
    var isPreDialogShown by rememberSaveable { mutableStateOf(false) }
    var isSufDialogShown by rememberSaveable { mutableStateOf(false) }
    var gradientId by rememberSaveable { mutableIntStateOf(0) }
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val maxLength = 15

    if(appViewModel.editableGradient!=null){
        prefixColorCode = appViewModel.editableGradient!!.prefixColor
        suffixColorCode = appViewModel.editableGradient!!.suffixColor
        gradientName = appViewModel.editableGradient!!.name
        gradientId = appViewModel.editableGradient!!.id
        isFavourite = appViewModel.editableGradient!!.isFavorite
        appViewModel.editableGradient = null
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.spacedBy(15.dp))
        {
            Button(onClick = {
//                    Toast.makeText(context,"pick 1 color",Toast.LENGTH_SHORT).show()
                    isPreDialogShown = true
                },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = " Pick Prefix Color", color = Color.Black)
            }
            if(isPreDialogShown){
                CustomDialog(onOk = {
                    Log.d("prefixColr",it)
                    prefixColorCode = it
                    isPreDialogShown = !isPreDialogShown
                }, onDismiss = {isPreDialogShown = !isPreDialogShown})
            }
            Canvas(modifier = Modifier.size(38.dp)) {
                drawCircle(color = Color(android.graphics.Color.parseColor(prefixColorCode)))
            }
            Text(
                text = prefixColorCode,
                color = Color.Black,
                fontSize = 17.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )
        }

        Row(modifier = Modifier.padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.spacedBy(15.dp))
        {
            Button(onClick = {
                isSufDialogShown = true;
            },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = " Pick Suffix Color", color = Color.Black)
            }
            if(isSufDialogShown){
                CustomDialog(onOk = {
                    Log.d("suffixColor",it)
                    suffixColorCode = it
                    isSufDialogShown = !isSufDialogShown
                }, onDismiss = {isSufDialogShown = !isSufDialogShown})
            }
            Canvas(modifier = Modifier.size(38.dp)) {
                drawCircle(color = Color(android.graphics.Color.parseColor(suffixColorCode)))
            }
            Text(text = suffixColorCode,
                color = Color.Black,
                fontSize = 17.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )
        }

        Column(modifier = Modifier
            .padding(top = 15.dp)
            .padding(horizontal = 50.dp)
            .padding(start = 5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {

            Text(
                text = "Gradient Name :",
                modifier = Modifier
                    .padding(bottom = 0.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                color = Color.Black
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = gradientName,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                onValueChange = {
                    if (it.length <= maxLength) gradientName = it
                },
                textStyle = TextStyle(fontSize = 16.sp),
                shape = RoundedCornerShape(8.dp),

                singleLine = true,
                placeholder = { Text(text = "Enter Gradient Name") },
                trailingIcon = {
                    if (gradientName.isNotEmpty()) {
                        IconButton(onClick = { gradientName = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
            Text(
                text = "${gradientName.length} / $maxLength",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.End,
                color = Color.Black
            )
        }
        Row(modifier = Modifier.padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.spacedBy(15.dp))
        {
            Button(
                onClick = {
                    if(prefixColorCode == suffixColorCode){
                        Toast.makeText(context,"Please pick different colors",Toast.LENGTH_SHORT).show()
                    }
                    else if(gradientName.isEmpty()){
                        Toast.makeText(context,"Please enter gradient name",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        appViewModel.addGradient(gradientId,gradientName,prefixColorCode,suffixColorCode,isFavourite)
                        navController.popBackStack()
                    }
                },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
               )
            {
                Image(
                    painter = painterResource(id = R.drawable.save_icon),
                    contentDescription = "Save Icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "SAVE",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradientManagerPagePreviewLight(){
    val navController = rememberNavController()
    GradientManager(navController, AppViewModel())
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GradientManagerPagePreviewDark(){
    val navController = rememberNavController()
    GradientManager(navController, AppViewModel())
}