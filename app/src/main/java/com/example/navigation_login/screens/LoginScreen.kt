package com.example.navigation_login.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.navigation_login.R



@Composable
fun LoginScreen(
    navController: NavHostController,
    isLoading: Boolean,
    onLoginClick: () -> Unit,

    ){
    val logo= painterResource(R.drawable.inicio)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            stringResource(R.string.Login_Title),
            style = MaterialTheme.typography.h3
        )
        Image(
            modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 32.dp),
            painter = logo,
            contentDescription =null
        )
        Text(
            stringResource(id = R.string.Sub_Title) ,
            style = MaterialTheme.typography.h3
        )
        if (isLoading){
            CircularProgressIndicator()
        }else {
            Button(onClick = onLoginClick ) {
                Text(stringResource(R.string.Login_cta))
            }//Fin del boton
        }
        LegalText()
    }//Fin de la columna
}//Fin Fun

@Composable
fun LegalText(){
    val anottatedString= buildAnnotatedString {
        append(stringResource(R.string.Text_Legal1))
        append(" ")
        pushStringAnnotation(tag = "URL", annotation = "app://terms")
        withStyle(
            style = SpanStyle( fontWeight = FontWeight.Bold,
                color= MaterialTheme.colors.secondary
            )
        ){
            append(stringResource(R.string.Text_Legal2))
        }
        append(" ")
        pop()
        append(stringResource(R.string.Text_Legal3))
        append(" ")
        pushStringAnnotation(tag = "URL", annotation = "app://privacy")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        ){
            append(stringResource(R.string.Text_Legal4))
        }
        pop()
    }
    Box(contentAlignment = Alignment.Center){
        ClickableText(
            modifier = Modifier.padding(24.dp),
            text = anottatedString){
                offset->anottatedString.getStringAnnotations("URL",offset,offset)
            .firstOrNull()?.let {
                    tag-> Log.d("App","Ha dado click en ${tag.item}")
            } //fin del toffset
        }//fin del text
    }//fin del Box
}//Fin Fun legal text