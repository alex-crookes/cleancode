package com.alexcrookes.provider.api.di

import com.alexcrookes.core.entities.AppConfig
import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.api.ApiImplementation
import com.alexcrookes.provider.local_preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@InstallIn(ViewModelComponent::class)
@InstallIn(SingletonComponent::class)
@Module
class ApiProviderModule {
	@Provides
	fun provideApiModule(
		preferences: Preferences,
		appConfig: AppConfig
	): Api {
		return ApiImplementation(
			prefs = preferences,
			consumerKey = appConfig.discogsConsumerKey,
			consumerSecret = appConfig.discogsConsumerSecret
		)
	}
}
