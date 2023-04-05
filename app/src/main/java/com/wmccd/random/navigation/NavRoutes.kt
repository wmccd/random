package com.wmccd.random.navigation

sealed class NavRoutes(val route: String){
    object Home: NavRoutes("home")
    object Contacts: NavRoutes("contacts")
    object RandomAlbum: NavRoutes("random_album")
}
