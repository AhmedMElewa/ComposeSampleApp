package com.elewa.composesampleapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elewa.composesampleapp.data.local.dao.ItemDao
import com.elewa.composesampleapp.data.local.database.AppDatabase
import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Module that holds database related classes
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    /**
     * Provides [AppDatabase] instance
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        itemProvider: Provider<ItemDao>,
    ): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        val itemDao = itemProvider.get()
                        if (itemDao.get().isEmpty()){
                            for (i in 0..10) {
                                var item = ItemEntity("item $i", "subtitle $i", "")
                                itemDao.insert(item)
                            }
                        }

                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideItemDao(appDatabase: AppDatabase) = appDatabase.itemDao()

}