package com.alexcrookes.cleancode.ui.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexcrookes.core.entities.Track
import com.alexcrookes.ui.molecules.HeadedSectionBlock
import com.alexcrookes.ui.molecules.TrackListing

@Composable
fun TrackList(
	tracks: List<Track>,
	modifier: Modifier = Modifier
) {
	require (tracks.isNotEmpty()) { return }

	HeadedSectionBlock(sectionName = "Tracks", modifier = modifier) {
		tracks.forEach {
			TrackListing(title = it.title, duration = it.duration, position = it.position)
		}
	}
}
