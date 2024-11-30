package com.alexcrookes.cleancode

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexcrookes.cleancode.ui.screens.ReleaseScreen
import com.alexcrookes.ui.CleanCodeTheme
import com.alexcrookes.domain.catalog.Catalog
import com.alexcrookes.domain.catalog.CatalogIntent
import com.alexcrookes.domain.catalog.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.coroutineScope

@HiltAndroidApp
class CCApplication: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		intent.action?.let { action ->
			Log.e("INTENT", "Received ${action}")
			require (action.equals(Intent.ACTION_VIEW)) { return@let  }

			intent.data?.let { uri ->
				val path = uri.path
				val args = uri.getQueryParameter("test")

				Log.e("INTENT", "Received $uri $path `$args`")
			}
		}

		enableEdgeToEdge()
		setContent {
			val catalogVm: Catalog = hiltViewModel<Catalog>()

			val state = catalogVm.releaseState.collectAsState()


			catalogVm.onIntent(CatalogIntent.GetRelease(249504))
			CleanCodeTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					when (val release = state.value) {
						is ViewModelResult.Loading ->
							LinearProgressIndicator()

						is ViewModelResult.Ready ->
							ReleaseScreen(release.data, Modifier)

						is ViewModelResult.Error ->
							Greeting(
								name = "Nope - ${release.exception.message}",
								modifier = Modifier.padding(innerPadding)
							)
					}

				}
			}
		}

	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	CleanCodeTheme {
		Greeting("Android")
	}
}
