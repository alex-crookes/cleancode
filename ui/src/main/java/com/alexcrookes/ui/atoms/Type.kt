package com.alexcrookes.ui.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val bodyLarge = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Normal,
	fontSize = 16.sp,
	lineHeight = 24.sp,
	letterSpacing = 0.5.sp
)

internal val headlineSmall = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Normal,
	fontSize = 24.sp,
	lineHeight = 32.sp,
	letterSpacing = 0.sp
)

internal val titleLarge = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Normal,
	fontSize = 22.sp,
	lineHeight = 28.sp,
	letterSpacing = 0.sp
)

internal val labelSmall = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Medium,
	fontSize = 11.sp,
	lineHeight = 16.sp,
	letterSpacing = 0.5.sp
)

// Set of Material typography styles to start with
val Typography = Typography(
	headlineSmall = headlineSmall,
	bodyLarge = bodyLarge,
    titleLarge = titleLarge,
    labelSmall = labelSmall,
)
