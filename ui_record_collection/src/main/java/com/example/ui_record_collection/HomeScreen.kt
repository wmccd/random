package com.example.ui_record_collection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel

@Composable
fun HomeScreen(viewModel: iRecordCollectionViewModel){
    val listState = viewModel.allAlbums.collectAsState(initial = listOf())
    HomeScreen(
        list = listState.value,
        onToggleFavourite = viewModel::toggleFavourite
    )
}

@Composable
internal fun HomeScreen(
    list: List<FavourableRecordVMModel>,
    onToggleFavourite: (id: String, favourite: Boolean ) -> Unit
){

    Box(
       modifier = Modifier.fillMaxSize()
    ){
        onToggleFavourite("", true)
        Column {
            when(list.size){
                0 -> noAlbumsExist()
                else -> albumsExist(
                    list = list,
                    onToggleFavourite = onToggleFavourite
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun albumsExist(
    list: List<FavourableRecordVMModel>,
    onToggleFavourite: (id: String, favourite: Boolean ) -> Unit
) {
    LazyColumn {
        items(list.size) { index ->
            val item = list[index]
            val nameAndArtist = "${item.recordVMModel.name} by ${item.recordVMModel.artist}"
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = nameAndArtist,
                    modifier = Modifier.weight(0.8f)
                )
                Checkbox(
                    checked = item.favourite,
                    onCheckedChange ={
                        onToggleFavourite(
                            item.recordVMModel.id.toString(),
                            it
                        )
                    },
                    modifier = Modifier.weight(0.2f)
                )
            }
            Spacer(
                modifier = Modifier.height(4.dp)
            )
        }
    }
}

@Composable
private fun noAlbumsExist() {
    Text(text = "No albums !?!!")

}