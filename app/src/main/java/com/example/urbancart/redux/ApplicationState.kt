package com.example.urbancart.redux

import com.example.urbancart.model.domain.Filter
import com.example.urbancart.model.domain.Product
import com.example.urbancart.model.domain.user.User

data class ApplicationState(
    val authState: AuthState = AuthState.Unauthenticated(),
    val products: List<Product> = emptyList(),
    val productFilterInfo: ProductFilterInfo = ProductFilterInfo(),
    val favoriteProductIds: Set<Int> = emptySet(),
    val expandedProductIds: Set<Int> = emptySet(),
    val inCartProductIds: Set<Int> = emptySet(),
    val cartQuantitiesMap: Map<Int, Int> = emptyMap(), // productId -> quantity
    val explorePageMetadata: ExplorePageMetadata = ExplorePageMetadata()
) {
    data class ProductFilterInfo(
        val filters: Set<Filter> = emptySet(),
        val selectedFilter: Filter? = null
    )

    data class ExplorePageMetadata(
        val selectedProductId: Int = 0
    )

    sealed interface AuthState {
        data class Authenticated(val user: User): AuthState
        data class Unauthenticated(val errorString: String? = null): AuthState

        fun getGreetingMessage(): String {
            return if (this is Authenticated) { user.greetingMessage } else { "Sign In" }
        }

        fun getEmail(): String {
            return if (this is Authenticated) { user.email } else { "" }
        }
    }
}
