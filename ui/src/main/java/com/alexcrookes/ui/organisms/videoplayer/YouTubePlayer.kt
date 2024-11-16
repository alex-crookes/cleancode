package com.alexcrookes.ui.organisms.videoplayer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeInternalPlayer(
	videoId: String,
	lifeCycleOwner: LifecycleOwner,
	modifier: Modifier = Modifier
) {
	AndroidView(
		modifier = modifier.fillMaxWidth().padding(8.dp),
		factory = { context ->
			YouTubePlayerView(context = context).apply {
				lifeCycleOwner.lifecycle.addObserver(this)
				addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
					override fun onReady(youTubePlayer: YouTubePlayer) {
						youTubePlayer.loadVideo(videoId, 0f)
					}
				})
			}
	})
}
