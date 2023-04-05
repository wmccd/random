package com.wmccd.random

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.wmccd.random.navigation.BarItem
import com.wmccd.random.navigation.NavRoutes

object HomeBarItem {
    val item = BarItem(
        title = "Home",
        image = Icons.Filled.Home,
        route = NavRoutes.Home.route
    )
}

object AddBarItem {
    val item = BarItem(
        title = "Add",
        image = Icons.Filled.AddCircle,
        route = NavRoutes.Contacts.route
    )
}

object FavouritesBarItem{
    val item = BarItem(
        title = "Favourites",
        image = Icons.Filled.Favorite,
        route = NavRoutes.Favourites.route
    )
}

object NavBarItems {
    val BarItems = listOf(
        HomeBarItem.item,
        AddBarItem.item,
        FavouritesBarItem.item
    )
}