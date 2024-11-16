package com.alexcrookes.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeadedSectionBlock(
	sectionName: String,
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit,
) {
	Column (
		modifier = modifier.padding(top = 32.dp)
	) {
		Text(
			modifier = Modifier.padding(bottom = 8.dp),
			text = sectionName,
			style = MaterialTheme.typography.titleSmall
		)

		content()
	}
}


@Composable
@Preview
private fun Preview_HeadedSectionBlock() {
	val sectionName = "Test Title"

	HeadedSectionBlock(sectionName = sectionName) {
		Text(text="The body")
	}
}
