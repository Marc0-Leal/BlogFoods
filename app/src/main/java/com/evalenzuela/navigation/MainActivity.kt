package com.evalenzuela.navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evalenzuela.navigation.navigation.BottomBar
import com.evalenzuela.navigation.navigation.BottomNavItem
import com.evalenzuela.navigation.navigation.Routes
import com.evalenzuela.navigation.ui.screens.DetailScreen
import com.evalenzuela.navigation.ui.screens.FavoriteScreen
import com.evalenzuela.navigation.ui.screens.HomeScreen
import com.evalenzuela.navigation.ui.screens.LoginScreen
import com.evalenzuela.navigation.ui.screens.ProfileScreen
import com.evalenzuela.navigation.ui.screens.RegisterScreen
import com.evalenzuela.navigation.ui.theme.NavigationTheme
import com.evalenzuela.navigation.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.Favorite)

    val startDestination = Routes.LOGIN

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute != Routes.LOGIN && currentRoute != Routes.REGISTER) {
                BottomBar(navController, bottomItems)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Routes.REGISTER)
                    }
                )
            }

            composable(Routes.REGISTER) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.REGISTER) { inclusive = true }
                        }
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.HOME) {
                val vm: MainViewModel = viewModel()
                HomeScreen(
                    viewModel = vm,
                    onItemClick = { id ->
                        navController.navigate(Routes.detailRoute(id))
                    }
                )
            }

            composable(Routes.FAVORITE) {
                FavoriteScreen()
            }

            composable(Routes.PROFILE) {
                ProfileScreen(navController = navController)
            }

            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val vm: MainViewModel = viewModel()
                val id = backStackEntry.arguments?.getInt("itemId") ?: -1
                DetailScreen(
                    itemId = id,
                    viewModel = vm,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
