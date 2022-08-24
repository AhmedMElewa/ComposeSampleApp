package com.elewa.composesampleapp.domain.usecase

import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.domain.qualifiers.IoDispatcher
import com.elewa.composesampleapp.domain.repository.ItemRepository
import com.elewa.composesampleapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<Resource<List<ItemEntity>>> {
        return itemRepository.fetchItems().flowOn(dispatcher)
    }
}
