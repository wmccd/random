package com.example.feature_viewmodel_record_collection.external

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_repository_record_collection.external.albums.iAlbumsRepository
import com.example.feature_repository_record_collection.external.favourites.iFavouritesRepository
import com.example.feature_viewmodel_record_collection.external.models.FavourableRecordVMModel
import com.example.feature_viewmodel_record_collection.external.models.RecordVMModel
import com.example.feature_viewmodel_record_collection.internal.RecordVMAndRepositoryModelConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class RecordCollectionViewModel(
    private val albumsRepository: iAlbumsRepository,
    private val favouritesRepository: iFavouritesRepository
): ViewModel(), iRecordCollectionViewModel {

    private val converter = RecordVMAndRepositoryModelConverter()

    override val allAlbums:  Flow<List<FavourableRecordVMModel>> = combine(
        albumsRepository.allAlbums,
        favouritesRepository.favouritesStream
    ){ albums, favourites ->
        albums.map { album ->
            val aFavourite = favourites.contains( album.id.toString())
            FavourableRecordVMModel(
                converter.convert(album),
                aFavourite
            )
        }
    }
    override val favouriteAlbums: Flow<List<FavourableRecordVMModel>> = combine(
        albumsRepository.allAlbums,
        favouritesRepository.favouritesStream
    ){ albums, favourites ->
        albums.map { album ->
            val aFavourite = favourites.contains( album.id.toString())
            FavourableRecordVMModel(
                converter.convert(album),
                aFavourite
            )
        }.filter { it.favourite }
    }
    override fun toggleFavourite(id: String, favourite: Boolean){
        viewModelScope.launch {
            favouritesRepository.toggleFavourite(
                id = id,
                favourite = favourite
            )
        }
    }

    override fun addAlbum(recordVMModel: RecordVMModel) {
        viewModelScope.launch {
            albumsRepository.insertAlbum(
                converter.convert(
                    recordVMModel = recordVMModel
                )
            )
        }
    }

    override fun deleteAlbum(id: Int) {
        viewModelScope.launch{
            albumsRepository.deleteAlbum(albumId = id)
        }
    }

}