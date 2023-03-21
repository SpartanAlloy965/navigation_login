package com.example.navigation_login.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigation_login.R
import com.example.navigation_login.modelos.Alumno
import com.example.navigation_login.modelos.AlumnosViewModel
import com.example.navigation_login.navegacion.AppScreens

@Composable
fun Alumno_Card(alumnos: Alumno){
    Card (
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Nombre ${alumnos.nombre}", color= Color.DarkGray, fontSize = 16.sp)
            Text(text = "Curso ${alumnos.curso}", color= Color.DarkGray, fontSize = 16.sp)
            Text(text = "Codigo ${alumnos.codigo.toString()} del alumno", color= Color.DarkGray, fontSize = 16.sp)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppAlumnos(navController: NavHostController, viewModel: AlumnosViewModel){

    val logo = painterResource(R.drawable.imagen)
    Scaffold(

        topBar = {
            TopAppBar {
                Icon(
                    imageVector = Icons.Default.AddCircle, 
                    contentDescription = "Menu Mapa",
                    tint = Color.White,
                    modifier = Modifier.clickable { navController.navigate(route = AppScreens.MapsScreen.route) }
                )
                Text(text = "Mapa")
                Box(modifier= Modifier) {
                    Column(modifier = Modifier.padding(2.dp)) {
                        Button(
                            onClick = { navController.navigate(route = AppScreens.Home.route) },

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.agregar),
                                contentDescription = "inicio"
                            )
                            Text(text = "principal", color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Button(onClick = { /*TODO*/ },
                    ) {
                    Image(painter = painterResource(id = R.drawable.read), contentDescription = "leer")
                    Text(text = "leer", color = Color.White)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton( modifier = Modifier.size(55.dp),
                onClick = { navController.navigate(route = AppScreens.AddScreen.route) }) {
                Icon(imageVector = Icons.Default.AddCircle,
                    contentDescription ="Agregar",
                    tint = Color.DarkGray
                )
            }
        },

        floatingActionButtonPosition = FabPosition.End
    ) {

        Box(modifier= Modifier.fillMaxSize()){
            Column() {

                Image(logo, contentDescription = "alumno",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                )
                LazyColumn(){
                    items(viewModel.alumnos.value){alumno->
                        Alumno_Card(alumno)
                    }
                }
            }
        }
    }
}