package com.example.feature_repository_record_collection.external.favourites

import android.util.Log
import com.wmccd.core_datastore.external.favourites.FavouritesDataSource
import com.wmccd.core_datastore.external.favourites.iFavouritesDataSource
import kotlinx.coroutines.flow.*

class MockFavouritesDataSource: iFavouritesDataSource {

    companion object{
        const val DIVIDER = "|"
    }

    var favorites = ""

    override val getFavourites:  Flow<List<String>>
        get() = flow {

            val list = arrayListOf<String>()

            when(favorites.length){
                0 -> {}
                else -> {
                    favorites.split(FavouritesDataSource.DIVIDER).filter{ it.isNotEmpty() }.forEach { item ->
                        list.add(item)
                    }
                }
            }
            list

            emit(list)
        }

    override suspend fun toggleFavourite(id: String, favourite: Boolean) {
        when (favourite) {
            true -> {
                favorites = "$id${FavouritesDataSource.DIVIDER}$favorites"
             }
            false -> {
                val removing = "$id${FavouritesDataSource.DIVIDER}"
                favorites = favorites.replace(removing, "")
            }
        }
    }


    override suspend fun clearFavourites() {
        favorites = ""
    }

}