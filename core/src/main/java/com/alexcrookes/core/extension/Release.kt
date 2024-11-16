package com.alexcrookes.core.extension

import com.alexcrookes.core.entities.Release
import com.alexcrookes.core.entities.RemoteImage

val Release.primaryImage: RemoteImage?
	get() = this.images.firstOrNull { it is RemoteImage.Primary }
