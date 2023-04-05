package com.example.ui_record_collection

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
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
                0 -> NoAlbumsExist()
                else -> AlbumsExist(
                    list = list,
                    onToggleFavourite = onToggleFavourite
                )
            }
        }
    }
}

@Composable
private fun AlbumsExist(
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
                FavouriteIcon(
                    isFavourite = item.favourite,
                    modifier = Modifier
                        .weight(0.2f)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                  onTap = {
                                    onToggleFavourite(
                                        item.recordVMModel.id.toString(),
                                        !item.favourite
                                    )
                                }
                            )
                        }
                )
            }
            Spacer(
                modifier = Modifier.height(4.dp)
            )
        }
    }
}

@Composable
private fun FavouriteIcon(isFavourite: Boolean, modifier: Modifier){
    when(isFavourite){
        true -> Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favourite",
            modifier = modifier,
            tint = Color.Red
        )
        else -> Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Not a Favourite",
            modifier = modifier,
            tint = Color.LightGray
        )
    }
}

@Composable
private fun NoAlbumsExist() {
    Text(text = "No albums !?!!")

}

@Composable
@Preview
fun FavouriteIconPreview_Favourite(){
    Column() {
        FavouriteIcon(isFavourite = true, modifier =Modifier )
        FavouriteIcon(isFavourite = false, modifier =Modifier )
    }
}

