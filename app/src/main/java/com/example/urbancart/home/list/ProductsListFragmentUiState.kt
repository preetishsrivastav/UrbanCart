package com.example.urbancart.home.list

import com.example.urbancart.model.ui.UiFilter
import com.example.urbancart.model.ui.UiProduct

sealed interface ProductsListFragmentUiState {

    data class Success(
        val filters: Set<UiFilter>,
        val products: List<UiProduct>
    ): ProductsListFragmentUiState

    object Loading: ProductsListFragmentUiState
}