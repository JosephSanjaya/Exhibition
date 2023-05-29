package com.joseph.exhibition.features.test.presentation.screen.test.viewmodel

import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.StateContainerImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class TestViewModelTest : StringSpec({
    // Mock the state factory and the state container
    val stateFactory = mockk<StateContainerFactory>()
    val stateContainer = mockk<StateContainerImpl>()
    // Mock the state delegate and the job
    val stateDelegate = mockk<MutableStateFlow<State<String>>>()
    val job = mockk<Job>()

    // Create the test view model with the mocked state factory
    // Set up the mock behavior for the state factory and the state container
    every { stateFactory.create(any()) } returns stateContainer
    every { stateContainer.getState(any(), any<State<String>>()) } returns stateDelegate
    every { stateContainer.processState("test", any<String>(), any()) } returns job

    val testViewModel = TestViewModel(stateFactory)

    "doTest should perform the first test when intent is DoTest1" {
        // Call the doTest function with DoTest1 intent
        testViewModel.doTest(TestIntent.DoTest1)
        // Verify that the doTestJob was set to the returned job
        testViewModel.doTestJob shouldBe job
    }

    "doTest should perform the second test when intent is DoTest2" {
        // Call the doTest function with DoTest2 intent
        testViewModel.doTest(TestIntent.DoTest2)
        // Verify that the doTestJob was set to the returned job
        testViewModel.doTestJob shouldBe job
    }
})
