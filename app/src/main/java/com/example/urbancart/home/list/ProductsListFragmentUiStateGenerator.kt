package com.example.urbancart.home.list

import com.example.urbancart.model.ui.UiFilter
import com.example.urbancart.model.ui.UiProduct
import com.example.urbancart.redux.ApplicationState
import javax.inject.Inject

class ProductsListFragmentUiStateGenerator @Inject constructor() {

    fun generate(
        uiProducts: List<UiProduct>,
        productFilterInfo: ApplicationState.ProductFilterInfo
    ): ProductsListFragmentUiState {
        if (uiProducts.isEmpty()) {
            return ProductsListFragmentUiState.Loading
        }

        val uiFilters = productFilterInfo.filters.map { filter ->
            UiFilter(
                filter = filter,
                isSelected = productFilterInfo.selectedFilter?.equals(filter) == true
            )
        }.toSet()

        val filteredProducts = if (productFilterInfo.selectedFilter == null) {
            uiProducts
        } else {
            uiProducts.filter { it.product.category == productFilterInfo.selectedFilter.value }
        }

        return ProductsListFragmentUiState.Success(uiFilters, filteredProducts)
    }
}