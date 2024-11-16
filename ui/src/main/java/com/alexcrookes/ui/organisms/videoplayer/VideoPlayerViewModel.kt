package com.alexcrookes.ui.organisms.videoplayer

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor() : ViewModel() {
	private val _playerState = MutableStateFlow<ExoPlayer?>(null)
	val playerState: StateFlow<ExoPlayer?> = _playerState.asStateFlow()

	private var currentPosition = 0L

	fun initializePlayer(context: Context, videoUrl: String) {
		require (_playerState.value == null) { return }

		viewModelScope.launch {
			val exoPlayer = ExoPlayer.Builder(context).build().also {
				val media = MediaItem.fromUri(Uri.parse(videoUrl))
				it.setMediaItem(media)
				it.prepare()
				it.playWhenReady = true
				it.seekTo(currentPosition)
				it.addListener(object: Player.Listener {
					override fun onPlayerError(error: PlaybackException) {
						handleError(error)
					}
				})
			}
			_playerState.value = exoPlayer
		}
	}

	fun savePlayerState() {
		_playerState.value?.let {
			currentPosition = it.currentPosition
		}
	}

	fun releasePlayer() {
		_playerState.value?.release()
		_playerState.value = null
	}

	private fun handleError(error: PlaybackException) {
		when (error.errorCode) {
			PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED -> {
				// Handle network connection error
				println("Network connection error")
			}

			PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND -> {
				// Handle file not found error
				println("File not found")
			}

			PlaybackException.ERROR_CODE_DECODER_INIT_FAILED -> {
				// Handle decoder initialization error
				println("Decoder initialization error")
			}

			else -> {
				// Handle other types of errors
				println("Other error: ${error.message}")
			}
		}
	}
}