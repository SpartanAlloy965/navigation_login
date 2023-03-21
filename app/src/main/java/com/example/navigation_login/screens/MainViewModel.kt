package com.example.navigation_login.screens

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigation_login.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

    class MainViewModel: ViewModel() {
        val isLoading= MutableLiveData(false)
        val envia = MutableLiveData(false)

        fun isLoading() : LiveData<Boolean> = isLoading
        fun envia(): LiveData<Boolean> = envia

        fun LoginWithGoogle(activity: Activity) {
            isLoading.postValue(true)

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val client = GoogleSignIn.getClient(activity, gso)

            val signInInternet: Intent = client.signInIntent
            activity.startActivityForResult(signInInternet, 1)

        }

        fun finishLogin(accountTask: Task<GoogleSignInAccount>) {
            try {
                val account: GoogleSignInAccount? = accountTask.getResult(ApiException::class.java)
                account?.idToken?.let {
                        token ->
                    val auth = FirebaseAuth.getInstance()
                    val credential = GoogleAuthProvider.getCredential(token, null)
                    auth.signInWithCredential(credential).addOnCompleteListener {
                            task ->
                        if(task.isSuccessful){
                            var user = auth.currentUser
                            Log.d("OK", "Ingreso ${user?.displayName}")
                            envia.postValue(true)
                        }else{
                            Log.d("Error", "No se puede conectar")
                        }
                        isLoading.postValue(false)
                    }
                }
            } catch (e: ApiException) {
                Log.d(ContentValues.TAG, "signInResult:failed code" + e.statusCode)
                isLoading.postValue(false)
            }
        } //fin finishLogin
    }//finMainViewM

