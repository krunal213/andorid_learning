package com.app.learning.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.learning.view.ui.theme.LearningTheme

sealed class Screen(
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    object Feed : Screen(Icons.Outlined.Home, Icons.Filled.Home, "feed")
    object Search : Screen(Icons.Outlined.Search, Icons.Filled.Search, "search")
    object Reels : Screen(Icons.Outlined.PlayArrow, Icons.Filled.PlayArrow, "reels")
    object Notifications : Screen(Icons.Outlined.Favorite, Icons.Filled.Favorite, "notifications")
    object Account : Screen(Icons.Outlined.Person, Icons.Filled.Person, "account")
}

val screens = listOf(
    Screen.Feed,
    Screen.Search,
    Screen.Reels,
    Screen.Notifications,
    Screen.Account
)

@Composable
fun Home() {
    HomeContent()
}

@Composable
private fun HomeContent(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Black, contentColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                if (selected) screen.selectedIcon else screen.unSelectedIcon,
                                contentDescription = null
                            )
                        },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Feed.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Feed.route) { Feed() }
            composable(Screen.Search.route) { Search() }
            composable(Screen.Reels.route) { Reels() }
            composable(Screen.Notifications.route) { Notifications() }
            composable(Screen.Account.route) { Account() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    LearningTheme {
        HomeContent()
    }
}