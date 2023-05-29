package com.joseph.exhibition.core.ui.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

class NavRouteImpl constructor(private val route: String) : NavRoute {

    private val arguments = mutableListOf<NamedNavArgument>()
    private val deepLinks = mutableListOf<NavDeepLink>()

    /**
     * Adds a named navigation argument to the route.
     *
     * @param name The name of the argument.
     * @param type The NavType of the argument.
     * @param defaultValue The default value for the argument. Defaults to an empty string.
     * @return The current NavRouteBuilder instance.
     */
    override fun argument(name: String, type: NavType<*>, defaultValue: Any): NavRoute {
        arguments.add(
            navArgument(name) {
                this.type = type
                this.defaultValue = defaultValue
            }
        )
        return this
    }

    /**
     * Adds a deep link to the route using the existing arguments.
     *
     * @param uriPattern The URI pattern for the deep link.
     * @return The current NavRouteBuilder instance.
     */
    override fun deepLink(uriPattern: String): NavRoute {
        val argumentPlaceholders = arguments.joinToString("&") { "${it.name}={${it.name}}" }
        val deepLinkUri = "$uriPattern?$argumentPlaceholders"

        deepLinks.add(navDeepLink { this.uriPattern = deepLinkUri })
        return this
    }

    /**
     * Builds and returns the composed navigation route.
     *
     * @return The NavComposeRoute instance representing the composed navigation route.
     */
    override fun build(): Destination {
        var mutablePath = route
        arguments.forEachIndexed { index, s ->
            mutablePath += if (index == 0) "?" else "&"
            mutablePath += "${s.name}={${s.name}}"
        }
        return Destination(mutablePath, arguments.toList(), deepLinks.toList())
    }
}
