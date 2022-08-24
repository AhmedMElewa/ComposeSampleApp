package com.elewa.composesampleapp.data.local.repository


import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.data.local.source.ItemLocalDataSource
import com.elewa.composesampleapp.domain.repository.ItemRepository
import com.elewa.composesampleapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImp @Inject constructor(
    private val dataSource: ItemLocalDataSource,
) : ItemRepository {

    override suspend fun fetchItems(): Flow<Resource<List<ItemEntity>>> {
        return flow {
            try {
                val data = dataSource.fetchItems()
                if (data.isEmpty()) {
                    emit(Resource.Empty)
                } else {
                    emit(Resource.Success(data))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun searchItems(filter: String): Flow<Resource<List<ItemEntity>>> {
        return flow {
            try {
                var searchValue = '%'+filter+'%'
                 dataSource.searchItems(searchValue).collect{
                    var data = it
                     if (data.isEmpty()) {
                        emit(Resource.Empty)
                    } else {
                        emit(Resource.Success(data))
                    }
                }

            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override fun deleteItem(item: ItemEntity) {
        dataSource.deleteItem(item)
    }


}