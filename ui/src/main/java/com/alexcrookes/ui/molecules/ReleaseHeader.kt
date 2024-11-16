package com.alexcrookes.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.alexcrookes.ui.atoms.Black00
import com.alexcrookes.ui.atoms.Black40
import com.alexcrookes.ui.atoms.Typography
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReleaseHeader(
	artistName: String,
	releaseTitle: String,
	year: Int,
	image: String?,
	modifier: Modifier = Modifier
) {
	val height = 240.dp
	Box(
		modifier = modifier
			.height(height)
			.fillMaxWidth()
			.background(color = MaterialTheme.colorScheme.primary),
		contentAlignment = Alignment.BottomStart
	) {
		image?.let {
			GlideImage(
				model = it,
				//loading = placeholder(MaterialTheme.colorScheme.primary),
				contentDescription = "$artistName $releaseTitle",
				contentScale = ContentScale.FillHeight,
				modifier = Modifier.fillMaxSize(),
			)
		}
		Box(
			modifier = modifier
				.height(height)
				.fillMaxWidth()
				.background(
					brush = Brush.verticalGradient(
						colors = listOf(Black00, Black40)
					)
				)
		) { /*NO-OP - Gradient overlay only*/}
		Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
			Text(
				style =  Typography.headlineSmall.copy(
					color = MaterialTheme.colorScheme.inverseOnSurface,
					shadow = Shadow(
						MaterialTheme.colorScheme.onSurface,
						offset = Offset(4.0f, 4.0f),
						blurRadius = 4f
					)
				),
				text = releaseTitle
			)
			Text(
				style = Typography.titleSmall.copy(
					color = MaterialTheme.colorScheme.inverseOnSurface,
					shadow = Shadow(
						MaterialTheme.colorScheme.onSurface,
						offset = Offset(4.0f, 4.0f),
						blurRadius = 4f
					)
				),
				text = "$artistName - $year"
			)
		}
	}
}
