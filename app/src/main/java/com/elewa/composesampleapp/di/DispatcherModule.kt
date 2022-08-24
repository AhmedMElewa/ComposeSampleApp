package com.elewa.composesampleapp.di


import com.elewa.composesampleapp.domain.qualifiers.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Module that holds Dispatchers
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}