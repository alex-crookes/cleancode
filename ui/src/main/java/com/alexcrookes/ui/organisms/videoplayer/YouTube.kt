package com.alexcrookes.ui.organisms.videoplayer

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

fun watchYouTubeViaIntent(context: Context, url: String) {
	try {
		val id = url.split("v=")[1]
		val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
		context.startActivity(intent)
	} catch (e: ActivityNotFoundException) {
		val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
		context.startActivity(intent)
	} catch (e: Exception) {
		Log.e("VIDEO", "Unable to parse the URL")
	}
}
