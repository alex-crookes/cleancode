package com.alexcrookes.ui.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.ConfigurationCompat
import com.alexcrookes.ui.extension.humanScale
import java.util.Locale

@Composable
fun Community(
	rating: Double,
	numberOfRatings: Int,
	wantedBy: Int,
	ownedBy: Int,
	modifier: Modifier = Modifier
) {
	val configuration = LocalConfiguration.current
	val locale: Locale = ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.ENGLISH
	HeadedSectionBlock(
		sectionName = "Community"
	) {
		Row {
			Text(
				style = MaterialTheme.typography.bodySmall,
				text = "Owned by: ${ownedBy.humanScale(locale)} "
			)

			Text(
				style = MaterialTheme.typography.bodySmall,
				text = "Wanted by: ${wantedBy.humanScale(locale)} "
			)

			Text(
				style = MaterialTheme.typography.bodySmall,
				text = "Rated $rating by $numberOfRatings people"
			)
		}
	}

}
