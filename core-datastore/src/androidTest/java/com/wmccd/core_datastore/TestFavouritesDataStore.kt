package com.wmccd.core_datastore

import android.util.Log
import com.wmccd.core_datastore.store.iFavouritesDataStore
import kotlinx.coroutines.flow.*

class TestFavouritesDataStore: iFavouritesDataStore {

    var favorites = ""

    override val getFavourites: Flow<String>
        get() = flow {
            emit(favorites)
        }

//    suspend fun collector(){
//        emitter.collect{
//            Log.d("Flow", "Starting")
//        }
//    }
//
//    val emitter: Flow<String>
//        get() = flow {
//            emit(favorites)
//        }
//        .onStart { Log.d("Flow", "Starting") }
//        .onEach { Log.d("Flow", "Emitted") }
//        .onCompletion { Log.d("Flow", "Conceptually the same as finally in a try/catch") }
//        .catch { Log.d("Flow", "Caught an Exception $it") }
//        .filter{it.length>10}
//        .map { "$it$it" } //change it to something else




    override suspend fun saveFavourites(favouritesIds: String) {
        Log.d("XXX", "Saving favouritesIds:<$favouritesIds>")

        favorites = favouritesIds
    }

    override suspend fun clearFavourites() {
        favorites = ""
    }
}