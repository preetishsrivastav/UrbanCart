package com.example.urbancart.model.ui

import com.example.urbancart.model.domain.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false,
    val isExpanded: Boolean = false,
    val isInCart: Boolean = false
)
