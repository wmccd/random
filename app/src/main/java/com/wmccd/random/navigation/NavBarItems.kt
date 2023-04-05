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

object RandomAlbumBarItem{
    val item = BarItem(
        title = "Random Album",
        image = Icons.Filled.PlayArrow,
        route = NavRoutes.RandomAlbum.route
    )
}

object NavBarItems {
    val BarItems = listOf(
        HomeBarItem.item,
        AddBarItem.item,
        RandomAlbumBarItem.item
    )
}