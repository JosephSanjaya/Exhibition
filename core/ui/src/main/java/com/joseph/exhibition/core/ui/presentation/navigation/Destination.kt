package com.joseph.exhibition.core.ui.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

/**
 * Represents a composed navigation route with associated arguments and deep links.
 *
 * @param route The string representation of the route.
 * @param arguments The list of named navigation arguments associated with the route.
 * @param deepLinks The list of deep links associated with the route. Defaults to an empty list.
 */
data class Destination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList()
) {
    fun argsBuilder(vararg args: Pair<String, Any>?): String {
        var navigation = route
        args.forEach {
            if (it == null) return@forEach
            navigation = navigation.replace("{${it.first}}", it.second.toString(), true)
        }
        // Remove any remaining placeholders
        navigation = navigation.replace(Regex("\\{.*?\\}"), "").run {
            if (last() == '/') dropLast(1) else this
        }
        return navigation
    }
}
