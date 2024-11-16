package com.alexcrookes.cleancode.di

import com.alexcrookes.cleancode.BuildConfig
import com.alexcrookes.core.entities.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppConfigModule {
	@Singleton
	@Provides
	fun providesAppConfig() : AppConfig {
		return AppConfig(
			discogsConsumerKey = BuildConfig.DISCOGS_CONSUMER_KEY,
			discogsConsumerSecret = BuildConfig.DISCOGS_CONSUMER_SECRET
		)
	}
}
