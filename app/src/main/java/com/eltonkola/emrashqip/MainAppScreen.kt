package com.eltonkola.emrashqip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eltonkola.emrashqip.ui.FavoritesScreen
import com.eltonkola.emrashqip.ui.MainAppViewModel
import com.eltonkola.emrashqip.ui.NamesScreen
import com.eltonkola.emrashqip.ui.SettingsScreen
import com.eltonkola.emrashqip.ui.SwipeScreen


sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object Home : NavigationItem("Home", Icons.Rounded.Home, "Home")
    object Favorites : NavigationItem("Favorites", Icons.Rounded.Favorite, "Favorites")
    object Names : NavigationItem("All names", Icons.Rounded.List, "All names")
    object Settings : NavigationItem("Settings", Icons.Rounded.Settings, "Settings")
    }
@Composable
fun Navigations(viewModel: MainAppViewModel,  navController: NavHostController, tts:(String) -> Unit ) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            SwipeScreen(viewModel, tts)
        }
        composable(NavigationItem.Names.route) {
            NamesScreen(viewModel)
        }
        composable(NavigationItem.Favorites.route) {
            FavoritesScreen(viewModel)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(viewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Names,
        NavigationItem.Settings
    )
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Home.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon!!, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


    @Composable
    fun MainAppScreen( viewModel: MainAppViewModel,  navController: NavHostController, tts:(String) -> Unit ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(modifier = Modifier) {
                    BottomNavigationBar(navController = navController)
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(
                    PaddingValues(
                        0.dp,
                        0.dp,
                        0.dp,
                        innerPadding.calculateBottomPadding()
                    )
                )
            ) {
                Navigations(viewModel = viewModel, navController = navController, tts =tts)
            }
        }
    }