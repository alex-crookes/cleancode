package com.alexcrookes.repository.collection.di

import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.local_preferences.Preferences
import com.alexcrookes.provider.room.CollectionDao
import com.alexcrookes.repository.collection.CollectionRepository
import com.alexcrookes.repository.collection.CollectionRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CollectionRepositoryModule {
	@Singleton
	@Provides
	fun provideCollectionRepository(
		api: Api, collectionDao: CollectionDao, preferences: Preferences
	): CollectionRepository =
		CollectionRepositoryImplementation(
			api = api,
			collectionDao = collectionDao,
			prefs = preferences
		)
}
