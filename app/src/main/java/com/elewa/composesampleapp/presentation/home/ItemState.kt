package com.elewa.composesampleapp.presentation.home

import com.elewa.composesampleapp.data.local.entity.ItemEntity

data class ItemState(
    val searchText: String = "",
    val loading: Boolean = false,
    val list: List<ItemEntity> = emptyList(),
)

sealed class ItemSideEffect {
    data class Toast(val text: String) : ItemSideEffect()
    data class DeleteItem(val item: ItemEntity) : ItemSideEffect()
}
