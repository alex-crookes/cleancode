package com.alexcrookes.provider.local_preferences.di

import android.content.Context
import com.alexcrookes.provider.local_preferences.Preferences
import com.alexcrookes.provider.local_preferences.PreferencesImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalPreferencesModule {
	@Singleton
	@Provides
	fun provideLocalPreferences(@ApplicationContext context: Context): Preferences {
		return PreferencesImplementation(context)
	}
}
