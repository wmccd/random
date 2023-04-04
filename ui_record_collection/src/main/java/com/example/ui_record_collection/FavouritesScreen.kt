package com.example.ui_record_collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Favourites(){
    Box(
       modifier = Modifier.fillMaxSize()
    ){
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favourites",
            tint = Color.Red,
            modifier = Modifier.size(size = 150.dp).align(alignment = Alignment.Center)
        )
    }
}