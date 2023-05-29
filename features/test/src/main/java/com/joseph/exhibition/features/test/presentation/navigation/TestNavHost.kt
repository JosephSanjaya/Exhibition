package com.joseph.exhibition.features.test.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joseph.exhibition.core.ui.presentation.navigation.NavRoute
import com.joseph.exhibition.features.test.presentation.screen.test.TestScreenRoute

private val testRoute1 = NavRoute.builder("test1").build()
private val testRoute2 = NavRoute.builder("test2").build()

@Composable
internal fun TestNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = testRoute1.route,
    onBackPressed: () -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = testRoute1.route,
            deepLinks = testRoute1.deepLinks,
            arguments = testRoute1.arguments,
        ) {
            TestScreenRoute { navController.navigate(testRoute1.route) }
        }
        composable(
            route = testRoute2.route,
            deepLinks = testRoute2.deepLinks,
            arguments = testRoute2.arguments,
        ) {
            TestScreenRoute { navController.navigate(testRoute1.route) }
        }
    }
}
