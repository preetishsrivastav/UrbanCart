package com.example.urbancart.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbancart.hilt.repository.ProductsRepository
import com.example.urbancart.model.domain.Filter
import com.example.urbancart.model.domain.Product
import com.example.urbancart.redux.ApplicationState
import com.example.urbancart.redux.Store
import com.example.urbancart.redux.reducer.UiProductListReducer
import com.example.urbancart.redux.updater.UiProductFavoriteUpdater
import com.example.urbancart.redux.updater.UiProductInCartUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer,
    val uiProductFavoriteUpdater: UiProductFavoriteUpdater,
    val uiProductInCartUpdater: UiProductInCartUpdater,
    private val productsRepository: ProductsRepository,
    private val filterGenerator: FilterGenerator
) : ViewModel() {

    fun refreshProducts() = viewModelScope.launch {
        if (store.read { it.products }.isNotEmpty()) return@launch
        val products: List<Product> = productsRepository.fetchAllProducts()
        val filters: Set<Filter> = filterGenerator.generateFrom(products)
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products,
                productFilterInfo = ApplicationState.ProductFilterInfo(
                    filters = filters,
                    selectedFilter = applicationState.productFilterInfo.selectedFilter
                ),
                explorePageMetadata = ApplicationState.ExplorePageMetadata(
                    selectedProductId = products.getOrNull(0)?.id ?: 0
                )
            )
        }
    }
}