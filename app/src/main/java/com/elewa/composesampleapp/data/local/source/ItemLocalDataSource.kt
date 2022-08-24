package com.elewa.composesampleapp.data.local.source

import com.elewa.composesampleapp.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemLocalDataSource {

    fun fetchItems(): List<ItemEntity>
    fun searchItems(filter: String): Flow<List<ItemEntity>>
    fun deleteItem(item: ItemEntity)
}