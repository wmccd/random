package com.wmccd.core_datastore.store

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class Favourites(private val dataStore: iFavouritesDataStore) {

    companion object{
        const val DIVIDER = "|"
    }

    val favouritesStream : Flow<List<String>> = dataStore.getFavourites.map { it ->
        val list = arrayListOf<String>()

        when(it.length){
            0 -> {}
            else -> {
                it.split(DIVIDER).filter{ it.isNotEmpty() }.forEach { item ->
                    list.add(item)
                }
            }
        }

        list
    }

    suspend fun toggleFavourite(id: String, favourite: Boolean ) {

        when (favourite) {
            true -> {
                val favs = dataStore.getFavourites.first()
                dataStore.saveFavourites("$id$DIVIDER$favs")
            }
            false -> {
                val original = dataStore.getFavourites.first()
                val removing = "$id$DIVIDER"
                Log.d("XXX", "original:<$original> removing:<$removing>")
                val updated = original.replace(removing, "")
                Log.d("XXX", "updated:<$updated> removing:<$removing>")
                dataStore.saveFavourites("$updated")

            }
        }
    }
}