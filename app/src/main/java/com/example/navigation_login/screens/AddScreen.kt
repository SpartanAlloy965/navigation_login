package com.example.navigation_login.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.navigation_login.modelos.Alumno
import com.example.navigation_login.navegacion.AppScreens
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint( "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar {} },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(70.dp),
                onClick = { navController.navigate(AppScreens.Home.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "atras",
                    tint = Color.DarkGray
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){
        BodyContent(navController)
    }
}


@Composable
fun BodyContent( navController: NavController ) {
    var Alnombre by remember { mutableStateOf("") }
    var Alcurso by remember { mutableStateOf("") }
    var Alcodigo by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = Alnombre,
                onValueChange = { Alnombre = it },
                label = { Text(text = "Nombre") },
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = Alcurso,
                onValueChange = { Alcurso = it },
                label = { Text(text = "Grupo") },
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = Alcodigo,
                onValueChange = { Alcodigo = it },
                label = { Text(text = "Codigo") },
            )


            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(onClick = {
                val alumno = Alumno(Alnombre, Alcurso, Alcodigo.toInt())
                Firebase.firestore.collection("alumnos").add(alumno)
                navController.navigate(AppScreens.Home.route)
            },
                modifier= Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ){
                Text(text = "Add Alumno")
                Icon(imageVector = Icons.Default.Add, contentDescription = "agregar")
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier= Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Volver")
                Icon(imageVector = Icons.Default.Close, contentDescription = "Volver")
            }
        }
    }
}