package com.elewa.composesampleapp.domain.repository

import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun fetchItems(): Flow<Resource<List<ItemEntity>>>
    suspend fun searchItems(filter: String): Flow<Resource<List<ItemEntity>>>
    fun deleteItem(item: ItemEntity)

}