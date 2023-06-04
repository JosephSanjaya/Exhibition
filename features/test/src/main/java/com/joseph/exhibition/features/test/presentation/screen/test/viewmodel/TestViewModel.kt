package com.joseph.exhibition.features.test.presentation.screen.test.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainer
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.state
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * A [ViewModel] that handles the test logic and state management.
 *
 * @param stateFactory The factory that creates the [StateContainer] for this [ViewModel].
 */
@HiltViewModel
class TestViewModel @Inject constructor(
    stateFactory: StateContainerFactory,
) : ViewModel() {

    // The container that holds the states for this [ViewModel].
    private val states: StateContainer = stateFactory.create(viewModelScope)

    // The state that represents the test result.
    val testState by states.state("test", State(""))

    // The job that performs the test logic.
    private var _doTestJob: Job? = null
    val doTestJob get() = _doTestJob

    /**
     * Executes the test logic based on the given [intent].
     *
     * @param intent The [TestIntent] that specifies which test to perform.
     */
    fun doTest(intent: TestIntent) {
        _doTestJob = when (intent) {
            TestIntent.DoTest1 -> states.processState("test", State<String>(null)) {
                "Test 1"
            }
            TestIntent.DoTest2 -> states.processState("test", State<String>(null)) {
                "Test 2"
            }
        }
    }
}
