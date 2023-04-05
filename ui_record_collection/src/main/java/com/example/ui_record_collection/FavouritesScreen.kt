package com.example.ui_record_collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel

@Composable
fun Favourites(viewModel: iRecordCollectionViewModel){
    val listState = viewModel.allAlbums.collectAsState(initial = listOf())
    Favourites(
        list = listState.value,
        onToggleFavourite = viewModel::toggleFavourite
    )
}

@Composable
internal fun Favourites(
    list: List<FavourableRecordVMModel>,
    onToggleFavourite: (id: String, favourite: Boolean ) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favourites",
            tint = Color.Red,
            modifier = Modifier
                .size(size = 150.dp)
                .align(alignment = Alignment.Center)
        )
    }
}