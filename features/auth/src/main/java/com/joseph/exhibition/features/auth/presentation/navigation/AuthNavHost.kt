package com.joseph.exhibition.features.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joseph.exhibition.core.ui.presentation.navigation.NavRoute
import com.joseph.exhibition.features.auth.presentation.screen.register.RegisterScreenRoute

internal val registerRoute = NavRoute.builder("register").build()

@Composable
internal fun AuthNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = registerRoute.route,
    onBackPressed: () -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = registerRoute.route,
            deepLinks = registerRoute.deepLinks,
            arguments = registerRoute.arguments,
        ) {
            RegisterScreenRoute()
        }
    }
}
