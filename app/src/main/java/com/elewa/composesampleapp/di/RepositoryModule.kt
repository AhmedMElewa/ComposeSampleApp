package com.elewa.composesampleapp.di


import com.elewa.composesampleapp.data.local.repository.ItemRepositoryImp
import com.elewa.composesampleapp.data.local.source.ItemLocalDataSource
import com.elewa.composesampleapp.data.local.source.ItemsLocalDataSourceImp
import com.elewa.composesampleapp.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Module that holds Repository classes
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideItemLocalDataSource(itemDataSource: ItemsLocalDataSourceImp): ItemLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun provideItemRepository(itemRepository : ItemRepositoryImp) : ItemRepository

}