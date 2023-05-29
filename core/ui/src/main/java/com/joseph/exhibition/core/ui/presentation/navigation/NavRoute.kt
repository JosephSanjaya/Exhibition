package com.joseph.exhibition.core.ui.presentation.navigation

import androidx.navigation.NavType

/**
 * Builder class for composing navigation routes with associated arguments and deep links.
 * Example usage:
 * ```
 * val route = createNavRoute("test/test") {
 *     argument("supplierName", NavType.StringType)
 *     argument("transactionDateText", NavType.StringType)
 *     // Add more arguments...
 *
 *     deepLink("example://test")
 *     // Add more deep links...
 * }
 * ```
 * @param route The string representation of the route.
 */
interface NavRoute {

    /**
     * Adds a named navigation argument to the route.
     *
     * @param name The name of the argument.
     * @param type The NavType of the argument.
     * @param defaultValue The default value for the argument. Defaults to an empty string.
     * @return The current NavRouteBuilder instance.
     */
    fun argument(name: String, type: NavType<*>, defaultValue: Any = ""): NavRoute

    /**
     * Adds a deep link to the route using the existing arguments.
     *
     * @param uriPattern The URI pattern for the deep link.
     * @param argumentNames The names of the existing arguments associated with the deep link.
     * @return The current NavRouteBuilder instance.
     */
    fun deepLink(uriPattern: String): NavRoute

    /**
     * Builds and returns the composed navigation route.
     *
     * @return The NavComposeRoute instance representing the composed navigation route.
     */
    fun build(): Destination

    companion object {
        fun builder(route: String): NavRoute {
            return NavRouteImpl(route)
        }
    }
}
