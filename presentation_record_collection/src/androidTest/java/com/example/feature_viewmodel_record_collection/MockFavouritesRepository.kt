package com.example.feature_viewmodel_record_collection

import com.example.feature_repository_record_collection.external.favourites.iFavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockFavouritesRepository: iFavouritesRepository {

    private val list = arrayListOf<String>()

    override val favouritesStream: Flow<List<String>>
        get() = flow{
            emit(list)
        }

    override suspend fun toggleFavourite(id: String, favourite: Boolean) {

        when(favourite){
            true -> addToList(id)
            false -> list.remove(id)
        }
    }

    private fun addToList(id: String) {
        if (!list.contains(id))
            list.add(id)
    }
}