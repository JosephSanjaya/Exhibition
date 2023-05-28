package com.joseph.exhibition.core.common.domain.state

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StateContainerImplTest : StringSpec({

    val testKey = "testKey"
    val defaultValue = "defaultValue"
    val newStateValue = "newStateValue"

    // Create a mock CoroutineScope
    val mockScope = mockk<CoroutineScope>()

    // Create a mock CoroutineContext
    val mockContext = Dispatchers.Unconfined as CoroutineContext

    // Create the StateContainerImpl instance with mocks
    val stateContainer = spyk(
        StateContainerImpl(mockContext, mockScope),
        recordPrivateCalls = true
    )

    "getState should return the StateFlow instance for the given key" {
        stateContainer.getState(testKey, defaultValue).value shouldBe defaultValue
    }

    "setState should update the value of the StateFlow for the given key" {
        stateContainer.setState(testKey, newStateValue)
        stateContainer.getState(testKey, defaultValue).value shouldBe newStateValue
    }
})

