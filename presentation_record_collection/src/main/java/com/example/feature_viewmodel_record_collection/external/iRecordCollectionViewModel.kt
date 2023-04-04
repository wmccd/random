package com.example.feature_viewmodel_record_collection.external

import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel
import kotlinx.coroutines.flow.Flow

interface iRecordCollectionViewModel {

    val combinedData: Flow<List<FavourableRecordVMModel>>
    fun toggleFavourite(id: String, favourite: Boolean)
    fun addAlbum(recordVMModel: RecordVMModel)
    fun deleteAlbum( id: Int )
}