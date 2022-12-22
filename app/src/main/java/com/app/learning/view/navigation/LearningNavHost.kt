package com.app.learning.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.learning.view.Registration
import com.app.learning.view.StatefulLogin
import com.app.learning.view.StatefulSplash

@Composable
fun LearningNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { StatefulSplash(
            navigateToLogin = {
                navController.navigate("login")
            }
        ) }
        composable("login") { StatefulLogin(
            navigateToRegistration = {
                navController.navigate("registration")
            }
        ) }
        composable("registration") {
            Registration()
        }
    }
}