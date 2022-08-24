package com.elewa.composesampleapp.data.local.source

import com.elewa.composesampleapp.data.local.dao.ItemDao
import com.elewa.composesampleapp.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsLocalDataSourceImp @Inject constructor(
    private val itemDao: ItemDao,
) : ItemLocalDataSource {

    override fun fetchItems(): List<ItemEntity> {
        return itemDao.get()
    }

    override fun searchItems(filter: String): Flow<List<ItemEntity>> {
        return itemDao.search(filter)
    }

//    override fun searchItems(filter: String): Flow<List<ItemEntity>>> {
//        return itemDao.search(filter)
//    }

    override fun deleteItem(item: ItemEntity) {
        itemDao.deleteItem(item)
    }

}