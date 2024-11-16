package com.alexcrookes.ui.organisms.videoplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun MediaPlayerView(
	videoUrl: String,
	viewModel: VideoPlayerViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	val player by viewModel.playerState.collectAsState()

	LaunchedEffect(videoUrl) {
		viewModel.initializePlayer(context, videoUrl)
	}

	DisposableEffect(Unit) {
		onDispose {
			viewModel.savePlayerState()
			viewModel.releasePlayer()
		}
	}

	Column {
		MediaAndroidView(player)
		PlayerControls(player)
	}
}

@Composable
fun MediaAndroidView(player: ExoPlayer?) {
	AndroidView(
		modifier = Modifier.fillMaxWidth(),
		factory = { context ->
			PlayerView(context).apply {
				this.player = player
			}
		},
		update = { playerView ->
			playerView.player = player
		}
	)
}

@Composable
fun PlayerControls(player: ExoPlayer?) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(24.dp),
		horizontalAlignment = Alignment.Start,
		verticalArrangement =  Arrangement.Center
	) {
		Button(onClick = { player?.playWhenReady = true}) {
			Text("Play")
		}

		Button(onClick = { player?.playWhenReady = false}) {
			Text("Pause")
		}

		Button(onClick = { player?.seekTo(player.currentPosition - 10_000L) }) {
			Text("- 10s")
		}

		Button(onClick = { player?.seekTo(player.currentPosition + 10_000L) }) {
			Text("+ 10s")
		}
	}
}
