package com.app.learning.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.learning.view.ui.theme.LearningTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LearningNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "github") {
        composable("github") {
            GithubActions()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLearningNavHostPreview() {
    LearningTheme {
        LearningNavHost()
    }
}