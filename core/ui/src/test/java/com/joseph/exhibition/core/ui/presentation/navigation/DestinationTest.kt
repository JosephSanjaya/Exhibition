package com.joseph.exhibition.core.ui.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class DestinationTest : StringSpec({
    "argsBuilder should replace placeholders with arguments" {
        // Arrange
        val route = "user/{id}?name={name}&test={test}"
        val arguments = listOf(
            mockk<NamedNavArgument>(),
            mockk()
        )
        val deepLinks = listOf(
            mockk<NavDeepLink>(),
            mockk()
        )
        val destination = Destination(route, arguments, deepLinks)

        // Act
        val result = destination.argsBuilder("id" to 123, "name" to "Alice", "test" to "a")

        // Assert
        result shouldBe "user/123?name=Alice&test=a"
    }

    "argsBuilder should ignore null arguments on the middle" {
        // Arrange
        val route = "user/{id}?name={name}&test={test}"
        val arguments = listOf(
            mockk<NamedNavArgument>(),
            mockk()
        )
        val deepLinks = listOf(
            mockk<NavDeepLink>(),
            mockk()
        )
        val destination = Destination(route, arguments, deepLinks)

        // Act
        val result = destination.argsBuilder("id" to 123, null, "test" to "a")

        // Assert
        result shouldBe "user/123?name=&test=a"
    }

    "argsBuilder should ignore null arguments on end" {
        // Arrange
        val route = "user/{id}?name={name}&test={test}"
        val arguments = listOf(
            mockk<NamedNavArgument>(),
            mockk()
        )
        val deepLinks = listOf(
            mockk<NavDeepLink>(),
            mockk()
        )
        val destination = Destination(route, arguments, deepLinks)

        // Act
        val result = destination.argsBuilder("id" to 123, "name" to "joseph")

        // Assert
        result shouldBe "user/123?name=joseph&test="
    }
})
