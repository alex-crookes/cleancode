package com.alexcrookes.cleancode.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexcrookes.cleancode.ui.organisms.TrackList
import com.alexcrookes.core.entities.Rating
import com.alexcrookes.core.entities.Release
import com.alexcrookes.core.entities.RemoteImage
import com.alexcrookes.core.entities.Track
import com.alexcrookes.core.entities.TrackType
import com.alexcrookes.core.entities.Video
import com.alexcrookes.core.extension.primaryImage
import com.alexcrookes.ui.CleanCodeTheme
import com.alexcrookes.ui.molecules.Community
import com.alexcrookes.ui.molecules.HeadedSectionBlock
import com.alexcrookes.ui.molecules.ReleaseHeader
import com.alexcrookes.ui.molecules.VideoThumbnail
import com.alexcrookes.ui.organisms.videoplayer.MediaPlayerView
import com.alexcrookes.ui.organisms.videoplayer.YouTubeInternalPlayer
import com.alexcrookes.ui.organisms.videoplayer.watchYouTubeViaIntent

@Composable
fun ReleaseScreen(release: Release, modifier: Modifier) {

	Log.e("STATE", "Loading Release - $release")
	val context = LocalContext.current

	Scaffold () { _ ->
		CleanCodeTheme {
			Column(
				modifier = Modifier
					.background(color = MaterialTheme.colorScheme.background)
					.verticalScroll(rememberScrollState()),

			) {

				ReleaseHeader(
					artistName = release.artists.first(),
					releaseTitle = release.title,
					year = release.year,
					image = release.primaryImage?.display,
				)

				Column(
					modifier = modifier
						.padding(horizontal = 16.dp)
					//.padding(top = 32.dp)
				) {

					TrackList(tracks = release.tracks)


					Community(
						ownedBy = release.community.ownedBy,
						wantedBy = release.community.wantedBy,
						rating = release.community.rating.average,
						numberOfRatings = release.community.rating.count
					)

					release.videos.firstOrNull()?.let { video ->
						HeadedSectionBlock(sectionName = "Videos") {
							VideoThumbnail(
								image = release.primaryImage?.display!!,
								title = video.title,
								videoUrl = video.url,
								onClick = {
									Log.e("VIDEO", "VIdeo is ${video.title}")
									when (video) {
										is Video.YouTube ->
											watchYouTubeViaIntent(context, video.url)

										else -> {
											/*no-op - show Other Video Player...*/
										}
									}
								}
							)
						}
					}

//					Text(
					val video = "https://www.youtube.com/watch?v=DVInNB5sKDc"
					YouTubeInternalPlayer(videoId = "DVInNB5sKDc", lifeCycleOwner = LocalLifecycleOwner.current)
				}

			}

		}
	}
}


@Composable
@Preview
fun Preview_Release() {
	CleanCodeTheme {
		ReleaseScreen(release = testRelease, modifier = Modifier)
	}
}

private val testRelease = Release(
	id = 249504,
	title = "Never Gonna Give You Up",
	artists = listOf("Rick Astley"),
	year = 1987,
	uri = "https://www.discogs.com/release/249504-Rick-Astley-Never-Gonna-Give-You-Up",
	resourceUrl = "https://api.discogs.com/releases/249504",
	genres = listOf("Punk"),
	styles = listOf("Rock"),
	images = listOf(
		RemoteImage.Primary(
			uri = "https://i.discogs.com/-DPFA5hKT8i91jnjn4rLB1zSiuUBFTrGWspu1TpLV30/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI0OTUw/NC0xMzM0NTkyMjEy/LmpwZWc.jpeg",
			resourceUrl = "https://i.discogs.com/-DPFA5hKT8i91jnjn4rLB1zSiuUBFTrGWspu1TpLV30/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI0OTUw/NC0xMzM0NTkyMjEy/LmpwZWc.jpeg",
			uri150 = "https://i.discogs.com/HG2xChKN-rIHHSfgL53W9z2vJWeFknfevpOMwSHtIaM/rs:fit/g:sm/q:40/h:150/w:150/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI0OTUw/NC0xMzM0NTkyMjEy/LmpwZWc.jpeg",
			width = 600,
			height= 600
		)
	),
	tracks = listOf(
		Track(
			title = "Never Gonna Give You Up",
			duration = "3:32",
			position = "A",
			type = TrackType.Track,
			extraArtists = emptyList()
		),
		Track(
			position= "B",
			type = TrackType.Track,
			title="Never Gonna Give You Up (Instrumental)",
			duration= "3:30",
			extraArtists = emptyList()
		)
	),
	description = "Never, ever, ever, gonna give this up",
	videos = listOf(

		Video.YouTube(
			id = "DVInNB5sKDc",
			_url = "https://www.youtube.com/watch?v=DVInNB5sKDc",
		    _title = "Rick Astley - Never Gonna Give You Up (Cake Mix) [12'' maxi single]",
			_description = "Rick Astley – Never Gonna Give You Up\n\nSello: RCA – PT-41448\nFormato: Vinilo, 12\", 45 RPM, Maxi-Single\nPaís: Spain\nPublicado: 1987\nGénero: Electronic, Pop\nEstilo: Synth-pop\n\n*A  Never Gonna Give You Up (Cake Mix) 5:48*\n\nB1  Never Gonna Give You Up (Instru",
			_duration = 354,
			_embed = true
		),

	),
	community = com.alexcrookes.core.entities.Community(
		wantedBy = 10124,
		ownedBy = 4560,
		rating = Rating(count = 10, average = 5.00)
	)
)
