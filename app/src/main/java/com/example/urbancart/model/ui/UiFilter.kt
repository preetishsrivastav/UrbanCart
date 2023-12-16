package com.example.urbancart.model.ui

import com.example.urbancart.model.domain.Filter

data class UiFilter(
    val filter: Filter,
    val isSelected: Boolean
)
