package com.alexcrookes.cleancode.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {
	@Singleton
	@Provides
	fun providesCoroutineScope(
		@DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
	): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
}
