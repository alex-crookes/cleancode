package com.alexcrookes.domain.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexcrookes.core.entities.Release
import com.alexcrookes.repository.catalog.CatalogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Catalog @Inject constructor(
	private val repository: CatalogRepository
) : ViewModel() {

	private val _releaseState = MutableStateFlow<ViewModelResult<Release>>(
		ViewModelResult.Loading
	)
	val releaseState = _releaseState.asStateFlow()

	fun onIntent(intent: CatalogIntent) = when (intent) {
		is CatalogIntent.GetRelease ->
			getRelease(intent.releaseId)
	}

	private fun getRelease(releaseId: Long) {
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
