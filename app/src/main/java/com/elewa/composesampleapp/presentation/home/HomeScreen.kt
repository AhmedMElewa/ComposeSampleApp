package com.elewa.composesampleapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elewa.composesampleapp.presentation.home.component.ItemList
import com.elewa.composesampleapp.presentation.home.component.SearchTextField

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.container.stateFlow.collectAsState()

    val mContext = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                is ItemSideEffect.Toast -> Toast.makeText(mContext, it.text, Toast.LENGTH_SHORT)
                    .show()
                is ItemSideEffect.DeleteItem -> {

                }
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(text = "Sample Compose App"  ,modifier = Modifier.weight(1f).padding(8.dp))
        }
    },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    SearchTextField(
                        searchTxt = state.searchText,
                        modifier = modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        onValueChanged = { value ->
                            viewModel.onSearchTextChang(value)
                        }
                    )
                    if (state.list.isNotEmpty()) {
                        ItemList(
                            list = state.list,
                            onDelete = { item ->
                                viewModel.deleteItem(item)
                            },
                        )
                    }
                }
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    item {
//
//                    }
//
//
//                    }
//                }
                if (state.loading) {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
//                        CircularProgressAnimated()
                        CircularProgressIndicator()
                    }
                }

            }
        }
    )
}

