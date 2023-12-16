package com.example.urbancart.home.cart.epoxy

import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.TypedEpoxyController
import com.example.urbancart.epoxy.VerticalSpaceEpoxyModel
import com.example.urbancart.extensions.toPx
import com.example.urbancart.home.cart.CartFragment
import com.example.urbancart.home.cart.CartFragmentViewModel
import kotlinx.coroutines.launch

class CartFragmentEpoxyController(
    private val viewModel: CartFragmentViewModel,
    private val onEmptyStateClicked: () -> Unit
) : TypedEpoxyController<CartFragment.UiState>() {

    override fun buildModels(data: CartFragment.UiState?) {
        when (data) {
            null, is CartFragment.UiState.Empty -> {
                CartEmptyEpoxyModel(onClick = {
                    onEmptyStateClicked()
                }).id("empty_state").addTo(this)
            }
            is CartFragment.UiState.NonEmpty -> {
                data.products.forEachIndexed { index, uiProductInCart ->
                    addVerticalStyling(index)
                    CartItemEpoxyModel(
                        uiProductInCart = uiProductInCart,
                        horizontalMargin = 16.toPx(),
                        onFavoriteClicked = {
                            viewModel.viewModelScope.launch {
                                viewModel.store.update {
                                    return@update viewModel.uiProductFavoriteUpdater.update(
                                        productId = uiProductInCart.uiProduct.product.id,
                                        currentState = it
                                    )
                                }
                            }
                        },
                        onQuantityChanged = { newQuantity: Int ->
                            viewModel.viewModelScope.launch {
                                viewModel.store.update {
                                    return@update viewModel.uiProductQuantityUpdater.update(
                                        productId = uiProductInCart.uiProduct.product.id,
                                        newQuantity = newQuantity,
                                        currentState = it
                                    )
                                }
                            }
                        }
                    ).id(uiProductInCart.uiProduct.product.id).addTo(this)
                }
            }
        }
    }

    private fun addVerticalStyling(index: Int) {
        VerticalSpaceEpoxyModel(8.toPx()).id("top_space_$index").addTo(this)

        if (index != 0) {
            DividerEpoxyModel(horizontalMargin = 16.toPx()).id("divider_$index").addTo(this)
        }

        VerticalSpaceEpoxyModel(8.toPx()).id("bottom_space_$index").addTo(this)
    }
}