package com.alexcrookes.provider.room.di

import android.content.Context
import androidx.room.Room
import com.alexcrookes.provider.room.CollectionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CollectionDatabaseModule {

	@Singleton
	@Provides
	fun provideCollectionDatabase(
		@ApplicationContext app: Context
	): CollectionDatabase = Room.databaseBuilder(
		app,
		CollectionDatabase::class.java,
		"collection_database"
	).build()

	@Singleton
	@Provides
	fun provideCollectionDao(db: CollectionDatabase) = db.collectionDao()
}
