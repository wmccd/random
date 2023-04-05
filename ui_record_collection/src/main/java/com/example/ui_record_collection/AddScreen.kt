package com.example.ui_record_collection

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.feature_viewmodel_record_collection.external.iRecordCollectionViewModel
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel


@Composable
fun AddScreen(viewModel: iRecordCollectionViewModel){
    AddScreen(
        onAddAlbum = viewModel::addAlbum
    )
}
@Composable
fun AddScreen(onAddAlbum:(RecordVMModel) -> Unit){

    var aristText by remember { mutableStateOf(TextFieldValue("")) }
    var albumText by remember { mutableStateOf(TextFieldValue("")) }

    Box(
       modifier = Modifier.fillMaxSize()
    ){

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = aristText,
                label = { Text(text = "Artist name") },
                onValueChange = {
                    aristText = it
                }
            )

            OutlinedTextField(
                value = albumText,
                label = { Text(text = "Album name ") },
                onValueChange = {
                    albumText = it
                }
            )

            Button(
                onClick = {
                    if(aristText.text.isNotEmpty() && albumText.text.isNotEmpty()) {
                        onAddAlbum(
                            RecordVMModel(
                                artist = aristText.text,
                                name = albumText.text
                            )
                        )
                    }

                    albumText = TextFieldValue("")
                    aristText = TextFieldValue("")
                },
                content = { Text(text = "Add")}
            )

        }


    }
}