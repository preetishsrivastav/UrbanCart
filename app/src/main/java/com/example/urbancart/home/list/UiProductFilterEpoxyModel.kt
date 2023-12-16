package com.example.urbancart.home.list

import androidx.core.content.ContextCompat
import com.example.urbancart.R
import com.example.urbancart.databinding.EpoxyModelProductFilterBinding
import com.example.urbancart.epoxy.ViewBindingKotlinModel
import com.example.urbancart.model.domain.Filter
import com.example.urbancart.model.ui.UiFilter

data class UiProductFilterEpoxyModel(
    val uiFilter: UiFilter,
    val onFilterSelected: (Filter) -> Unit
) : ViewBindingKotlinModel<EpoxyModelProductFilterBinding>(R.layout.epoxy_model_product_filter) {

    override fun EpoxyModelProductFilterBinding.bind() {
        root.setOnClickListener { onFilterSelected(uiFilter.filter) }
        filterNameTextView.text = uiFilter.filter.displayText

        val cardBackgroundColorResId = if (uiFilter.isSelected) {
            R.color.purple_500
        } else {
            R.color.purple_200
        }
        root.setCardBackgroundColor(ContextCompat.getColor(root.context, cardBackgroundColorResId))
    }
}
