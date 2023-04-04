package com.example.ui_record_collection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel


@Composable
fun HomeScreen(viewModel: iRecordCollectionViewModel){
    val listState = viewModel.combinedData.collectAsState(initial = listOf())
    HomeScreen(
        list = listState.value,
        onToggleFavourite = viewModel::toggleFavourite
    )
}

@Composable
fun HomeScreen(
    list: List<FavourableRecordVMModel>,
    onToggleFavourite: (id: String, favourite: Boolean ) -> Unit
){

    Box(
       modifier = Modifier.fillMaxSize()
    ){
        onToggleFavourite("", true)
        Column() {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "home",
                tint = Color.Blue,
                modifier = Modifier.size(size = 150.dp)
            )
            LazyColumn {
                items(list.size) { index ->
                   Text(
                       text = list[0].recordVMModel.name
                   )
                }
            }
        }

    }
}