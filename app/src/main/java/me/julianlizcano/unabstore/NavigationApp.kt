package me.julianlizcano.unabstore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Preview
@Composable

fun NavigationApp() {

    val navControler = rememberNavController()
    var startDestination = "login"

    val auth = Firebase.auth
    var currentUser = auth.currentUser

    if (currentUser != null){
        startDestination = "home"
    }else{
        startDestination = "login"
    }


    NavHost(
        navController = navControler,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    )
    {
        composable(route = "login") {
            LoginScreen(onClickRegister = {
                navControler.navigate("Register")

            }, onSuccessfulLogin = {
                navControler.navigate("home"){
                    popUpTo("login"){inclusive = true}
                }
            }
            )
        }
        composable(route = "register") {
            RegisterScreen(onClickBack = {
                navControler.popBackStack()
            }, onSuccessfulRegister = {
                navControler.navigate("home"){
                    popUpTo(0)
                }
            })
        }
        composable(route = "home") {
            HomeScreen(onClickLogout = {
                navControler.navigate("login"){
                    popUpTo(0)
                }
            })
        }
    }
}



