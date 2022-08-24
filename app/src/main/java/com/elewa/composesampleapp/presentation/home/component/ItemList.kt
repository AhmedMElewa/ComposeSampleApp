package com.elewa.composesampleapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elewa.composesampleapp.data.local.entity.ItemEntity

@Composable
fun ItemList(
    list: List<ItemEntity>,
    onDelete: (ItemEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            ItemView(
                title = item.title,
                subTitle = item.subTitle,
                onDelete = { onDelete(item) }
            )
        }
    }
}
