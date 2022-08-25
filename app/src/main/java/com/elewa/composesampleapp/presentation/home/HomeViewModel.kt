package com.elewa.composesampleapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elewa.composesampleapp.data.local.entity.ItemEntity
import com.elewa.composesampleapp.domain.usecase.DeleteItemsUseCase
import com.elewa.composesampleapp.domain.usecase.FetchItemsUseCase
import com.elewa.composesampleapp.domain.usecase.SearchItemsUseCase
import com.elewa.composesampleapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchItemsUseCase: FetchItemsUseCase,
    private val searchItemsUseCase: SearchItemsUseCase,
    private val deleteItemsUseCase: DeleteItemsUseCase
) : ContainerHost<ItemState, ItemSideEffect>, ViewModel() {

    override val container = container<ItemState, ItemSideEffect>(
        ItemState(),
        settings = Container.Settings(intentDispatcher = Dispatchers.Unconfined)
    )


    init {
        intent {
            onSearchTextChang(state.searchText)
        }
    }

    fun onSearchTextChang(value: String) = intent {
        try {
//            if (value.isEmpty()){
//                fetchItems()
//            }else{
            searchItemsUseCase.invoke(value).onStart {
                reduce {
                    state.copy(
                        loading = true,
                        searchText = value
                    )
                }
                delay(1000)
            }.collect {
                when (it) {
                    is Resource.Loading -> {
                        reduce {
                            state.copy(
                                loading = true,
                            )
                        }
                    }
                    is Resource.Success -> {
                        reduce {
                            var data = it.data
                            state.copy(
                                loading = false,
                                list = it.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        reduce {
                            state.copy(loading = false)
                        }

                        postSideEffect(ItemSideEffect.Toast(it.exception.message.toString()))
                    }
                    is Resource.Empty -> {
                        reduce {
                            state.copy(
                                loading = false,
                                list = emptyList()
                            )
                        }
                        postSideEffect(ItemSideEffect.Toast("No items found!"))
                    }
                }
            }
//            }

        } catch (e: Exception) {
            postSideEffect(ItemSideEffect.Toast(e.message.toString()))
        }

    }


    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteItemsUseCase.invoke(item)
        }

    }

//    private fun fetchItems() = intent {
//        try {
//            //fetchData
//            fetchItemsUseCase.invoke().onStart {
//                reduce {
//                    state.copy(
//                        loading = true,
//                        searchText = "")
//                }
//            }.collect {
//                when (it) {
//                    is Resource.Loading -> {
//                        reduce {
//                            state.copy(loading = true)
//                        }
//                    }
//                    is Resource.Success -> {
//                        reduce {
//                            state.copy(
//                                loading = false, list = it.data
//                            )
//                        }
//                    }
//                    is Resource.Error -> {
//                        reduce {
//                            state.copy(loading = false)
//                        }
//
//                        postSideEffect(ItemSideEffect.Toast(it.exception.message.toString()))
//                    }
//                    is Resource.Empty -> {
//                        reduce {
//                            state.copy(loading = false)
//                        }
//                        postSideEffect(ItemSideEffect.Toast("No items found!"))
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            postSideEffect(ItemSideEffect.Toast(e.message.toString()))
//        }

//}

}