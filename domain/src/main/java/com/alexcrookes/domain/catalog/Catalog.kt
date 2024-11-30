package com.alexcrookes.domain.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.core.entities.ListRelease
import com.alexcrookes.core.entities.Release
import com.alexcrookes.repository.catalog.CatalogRepository
import com.alexcrookes.repository.collection.CollectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Catalog @Inject constructor(
	private val repository: CatalogRepository,
	private val collection: CollectionRepository

) : ViewModel() {

	private val _releaseState = MutableStateFlow<ViewModelResult<Release>>(
		ViewModelResult.Loading
	)
	val releaseState = _releaseState.asStateFlow()

	fun onIntent(intent: CatalogIntent) = when (intent) {
		is CatalogIntent.GetRelease ->
			getRelease(intent.releaseId)

	}

	private fun checkRoom() {
		val collectionRelease = CollectionRelease(
			id = 1234,
			instanceId = 12345,
			dateAdded = System.currentTimeMillis(),
			rating = 5.0,
			folder = 1,
			listRelease = ListRelease(
				id = 1234,
				masterId = 123456,
				resourceUrl = "https://resource.url",
				thumbnail = "https://thumbnail.url",
				coverImage = "https://coverimage.url",
				title = "track title",
				year = 2001,
				formats = emptyList(),
				artists = emptyList(),
				labels = emptyList(),
				genres = emptyList(),
				styles = emptyList(),
			)
		)
		viewModelScope.launch {
			val oldRows = collection.collectionContains(collectionRelease.id)
			Log.e("ROOM", "Collection contains? $oldRows")
			collection.TEST_insert(collectionRelease)
			val rows = collection.collectionContains(collectionRelease.id)

			Log.e("ROOM", "Collection contains? $rows")
		}
	}

	private fun getRelease(releaseId: Long) {
		viewModelScope.launch {
			checkRoom()
		}
		viewModelScope.launch {
			Log.e("VM", "Getting release $releaseId")
			val result = repository.getReleaseDetails(releaseId)
			Log.e("VM", "Got release")
			require (result.isSuccess) {
				Log.e(
					"VM",
					"Got release Error - ${result.exceptionOrNull()?.message ?: "nothing found"}")
				_releaseState.emit( ViewModelResult.Error(Exception("Unknown Error")))
				return@launch
			}

			result.getOrNull()?.let {
				Log.e("VM", "Got release - ${it.id}")
				_releaseState.emit( ViewModelResult.Ready(it) )
			} ?: run {
				Log.e("VM", "Got release - NULL Result")
				_releaseState.emit(
					ViewModelResult.Error(Exception("Unknown Error"))
				)
			}
		}
	}
}
