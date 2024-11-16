package com.alexcrookes.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcrookes.ui.CleanCodeTheme

@Composable
fun TrackListing(
	title: String,
	duration: String,
	position: String,
	modifier: Modifier = Modifier
) {
	Row (
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier.padding(vertical = 4.dp)
	){
		Text(
			modifier = Modifier.padding(end = 8.dp),
			text = position,
			style = MaterialTheme.typography.bodySmall,
			textAlign = TextAlign.Start
		)
		Text(
			modifier = Modifier.weight(1f),
			maxLines = 2,
			text = title,
			style = MaterialTheme.typography.bodyLarge.copy(
				color = MaterialTheme.colorScheme.primary
			)
		)
		Text(
			modifier = Modifier.padding(start = 8.dp),
			text = duration,
			style = MaterialTheme.typography.bodySmall,
			textAlign = TextAlign.End
		)
	}

}


@Composable
@Preview
fun PREVIEW_Track() {
	CleanCodeTheme {
		TrackListing(title = "Song Title!!!", duration="13:32", position = "A1")
	}
}
