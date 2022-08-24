package com.elewa.composesampleapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elewa.composesampleapp.data.local.dao.ItemDao
import com.elewa.composesampleapp.data.local.entity.ItemEntity

@Database(
    entities = [
        (ItemEntity::class),
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

}