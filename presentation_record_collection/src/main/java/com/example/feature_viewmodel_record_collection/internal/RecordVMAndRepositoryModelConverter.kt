package com.example.feature_viewmodel_record_collection.internal

import com.example.feature_repository_record_collection.external.albums.models.AlbumsRepositoryModel
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel

internal class RecordVMAndRepositoryModelConverter {

    fun convert( repositoryModel: AlbumsRepositoryModel): RecordVMModel {
        return RecordVMModel(
            id =  repositoryModel.id,
            artist = repositoryModel.artist,
            name = repositoryModel.name
        )
    }

    fun convert( recordVMModel: RecordVMModel): AlbumsRepositoryModel{
        return AlbumsRepositoryModel(
            id = recordVMModel.id,
            artist = recordVMModel.artist,
            name = recordVMModel.name
        )
    }
}