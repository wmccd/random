package com.example.ui_record_collection

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel
import kotlin.random.Random

@Composable
fun RandomAlbumScreen(viewModel: iRecordCollectionViewModel){

    val listState = viewModel.allAlbums.collectAsState(initial = listOf())
    Log.d("XXX", "RandomAlbumScreen $listState")

    RandomAlbumScreen(
        list = listState.value,
        onToggleFavourite = viewModel::toggleFavourite
    )
}

@Composable
internal fun RandomAlbumScreen(
    list: List<FavourableRecordVMModel>,
    onToggleFavourite: (id: String, favourite: Boolean ) -> Unit
){
    when(list.size){
        0 -> return
    }

    val randomIndex = Random.nextInt(list.size);
    val randomItem = list[randomIndex]

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = randomItem.recordVMModel.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text("by")
        Text(randomItem.recordVMModel.artist)
    }

//    Box(
//        modifier = Modifier.fillMaxSize()
//    ){
//        Icon(
//            imageVector = Icons.Filled.Favorite,
//            contentDescription = "Favourites",
//            tint = Color.Red,
//            modifier = Modifier
//                .size(size = 150.dp)
//                .align(alignment = Alignment.Center)
//        )
//    }
}