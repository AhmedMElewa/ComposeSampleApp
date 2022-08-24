package com.elewa.composesampleapp.data.local.dao

import androidx.room.*
import com.elewa.composesampleapp.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(item: ItemEntity)

    @Query("SELECT * from items")
    fun get(): List<ItemEntity>

    @Query("SELECT * from items WHERE title LIKE  :filter   ORDER BY id")
    fun search(filter: String): Flow<List<ItemEntity>>

    @Delete
    fun deleteItem(item: ItemEntity)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}