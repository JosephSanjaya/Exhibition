package com.joseph.exhibition.core.ui.presentation.navigation

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic

class NavRouteTest : StringSpec({
    "NavRouteBuilder should build correct NavComposeRoute" {
        // Create mock arguments and deep links
        mockkStatic("android.net.Uri")
        val uri = mockk<Uri>(relaxed = true)

        every { any<String>().toUri() } returns mockk()
        mockkStatic(Uri::class)
        every { Uri.parse(any()) } returns uri
        val expected = "test/test?arg1={arg1}&arg2={arg2}"
        every { uri.query } returns expected

        // Create NavRouteBuilder instance
        val builder = NavRoute.builder("test/test")
            .argument("arg1", NavType.StringType)
            .argument("arg2", NavType.IntType)
            .deepLink("example.com/test")

        // Build NavComposeRoute
        val navComposeRoute = builder.build()

        // Assert the route and arguments
        navComposeRoute.route shouldBe expected
    }
})
