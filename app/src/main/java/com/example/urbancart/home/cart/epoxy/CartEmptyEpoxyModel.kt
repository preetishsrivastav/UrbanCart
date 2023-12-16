package com.example.urbancart.home.cart.epoxy

import android.view.View
import com.example.urbancart.R
import com.example.urbancart.databinding.EpoxyModelCartEmptyBinding
import com.example.urbancart.epoxy.ViewBindingKotlinModel

data class CartEmptyEpoxyModel(
    private val onClick: (View) -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartEmptyBinding>(R.layout.epoxy_model_cart_empty) {

    override fun EpoxyModelCartEmptyBinding.bind() {
        button.setOnClickListener(onClick)
    }
}
