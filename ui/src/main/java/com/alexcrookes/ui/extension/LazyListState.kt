package com.alexcrookes.ui.extension

import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.scrollToTop() {
	animateScrollToItem(0)
}
