package com.wmccd.random

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Contacts: NavRoutes("contacts")
    object Favourites: NavRoutes("favourites")
}
