package com.alexcrookes.domain.catalog

sealed class ViewModelResult<out T>  {
	data object Loading : ViewModelResult<Nothing>()
	data class Error<out T> (val exception: Throwable): ViewModelResult<T>()
	data class Ready<out T> (val data: T): ViewModelResult<T>()
}
