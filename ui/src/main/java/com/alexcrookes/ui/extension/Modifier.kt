package com.alexcrookes.ui.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//fun Modifier.clickableWithRipple(onClick: () -> Unit): Modifier {
//
//	return this.clickable(	this.clickable(
//		indication = rememberRipple(bounded = true),
//		interactionSource = remember { MutableInteractionSource() },
//		onClick = onClick
//
//	)
//
//}

fun Modifier.roundedBackgroundWithPadding(
	backgroundColor: Color,
	cornerRadius: Dp,
	padding: Dp
): Modifier = this
	.background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
	.padding(padding)

fun Modifier.showIf(condition: Boolean): Modifier =
	if (condition) this else Modifier.size(0.dp)

fun Modifier.animateVisibility(isVisible: Boolean): Modifier =
	if (isVisible) {
		this.alpha(1f)
	} else {
		this.alpha(0f)
	}
