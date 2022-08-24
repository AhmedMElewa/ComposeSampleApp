package com.elewa.composesampleapp.domain.usecase

import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.domain.qualifiers.IoDispatcher
import com.elewa.composesampleapp.domain.repository.ItemRepository
import com.elewa.composesampleapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.logging.Filter
import javax.inject.Inject

class DeleteItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    operator fun invoke(item:ItemEntity) {
         itemRepository.deleteItem(item)
    }
}
