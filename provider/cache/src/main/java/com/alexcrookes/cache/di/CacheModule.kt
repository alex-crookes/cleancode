package com.alexcrookes.cache.di

import com.alexcrookes.cache.Cache
import com.alexcrookes.cache.CacheImplementation
import com.alexcrookes.provider.local_preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
	@Singleton
	@Provides
	fun provideCache(preferences: Preferences): Cache {
		return CacheImplementation(preferences)
	}
}
