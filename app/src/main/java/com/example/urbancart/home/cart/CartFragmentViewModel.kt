package com.example.urbancart.home.cart

import androidx.lifecycle.ViewModel
import com.example.urbancart.redux.ApplicationState
import com.example.urbancart.redux.Store
import com.example.urbancart.redux.reducer.UiProductListReducer
import com.example.urbancart.redux.updater.UiProductFavoriteUpdater
import com.example.urbancart.redux.updater.UiProductInCartUpdater
import com.example.urbancart.redux.updater.UiProductQuantityUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer,
    val uiProductFavoriteUpdater: UiProductFavoriteUpdater,
    val uiProductInCartUpdater: UiProductInCartUpdater,
    val uiProductQuantityUpdater: UiProductQuantityUpdater
): ViewModel() {
}