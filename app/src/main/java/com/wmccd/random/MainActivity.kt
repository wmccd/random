package com.wmccd.random

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui_record_collection.AddScreen
import com.example.ui_record_collection.RandomAlbumScreen
import com.example.ui_record_collection.HomeScreen
import com.wmccd.random.injection.Injection
import com.wmccd.random.navigation.NavRoutes
import com.wmccd.random.ui.theme.RandomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar (
                title = {Text("Record Collection")}
            )
        },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Composable
fun NavigationHost(navController: NavHostController){

    val injection = Injection()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ){
        composable(NavRoutes.Home.route)  {
            HomeScreen(
                viewModel = injection.recordCollectionViewModel
            )
        }
        composable(NavRoutes.Contacts.route)  {
            AddScreen(
                viewModel = injection.recordCollectionViewModel
            )
        }
        composable(NavRoutes.RandomAlbum.route)  {
            RandomAlbumScreen(
                viewModel = injection.recordCollectionViewModel
            )
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController){
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(
                        route = navItem.route
                    ){
                        popUpTo( id = navController.graph.findStartDestination().id){
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(
                        text = navItem.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomTheme {
        MainScreen()
    }
}
