package com.alexcrookes.repository.catalog.di

import com.alexcrookes.cache.Cache
import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.local_preferences.Preferences
import com.alexcrookes.repository.catalog.CatalogRepository
import com.alexcrookes.repository.catalog.CatalogRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@InstallIn(ViewModelComponent::class)
@InstallIn(SingletonComponent::class)
@Module
class CatalogRepositoryModule {
	@Provides
	fun providesCatalogRepository(
		api: Api, preferences: Preferences, cache: Cache
	): CatalogRepository {
		return CatalogRepositoryImplementation(api = api, preferences = preferences, cache = cache)
	}
}
