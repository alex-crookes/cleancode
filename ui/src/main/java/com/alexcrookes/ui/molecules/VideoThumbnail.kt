package com.alexcrookes.ui.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideoThumbnail(
	image: String,
	title: String,
	videoUrl: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.height(120.dp)
			.width(80.dp)
			.clickable { onClick() },
	) {
		Row {
			GlideImage(
				modifier = Modifier.size(80.dp),
				model = image,
				contentDescription = title,
				contentScale = ContentScale.Fit,
			)
		}
		Row {
			Text(
				modifier = Modifier.weight(1f),
				text = title,
				style = MaterialTheme.typography.labelSmall,
				maxLines = 2
			)
		}
	}
}

@Composable
@Preview
private fun Preview_VideoThumbnail() {
	val image = "https://image.com/"
	val title = "I am a video title."
	val videoUrl = "https:///videoUrl"
	val onClick = {}

	VideoThumbnail(
		image = image,
		title = title,
		videoUrl = videoUrl,
		onClick = onClick
	)
}
