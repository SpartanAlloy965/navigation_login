package com.example.navigation_login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.navigation_login.navegacion.AppNavigation
import com.example.navigation_login.screens.MainViewModel
import com.example.navigation_login.ui.theme.Navigation_loginTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel by viewModels()
            val isLoading by viewModel.isLoading().observeAsState(false)
            val envia by viewModel.envia().observeAsState(false)
            Navigation_loginTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(
                        isLoading = isLoading,
                        envia = envia,
                        onClick = { viewModel.LoginWithGoogle(this@MainActivity) }
                    )
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val viewModel: MainViewModel by viewModels()

        if (requestCode == 1) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.finishLogin(task)
        }
    }
}


