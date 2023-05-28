package com.joseph.exhibition.core.ui.presentation.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainer
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.state
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    stateFactory: StateContainerFactory
) : ViewModel() {
    private val states: StateContainer = stateFactory.create(viewModelScope)
    val testState by states.state<State<String>>("test", State(null))
    private var _doTestJob: Job? = null
    val doTestJob get() = _doTestJob

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
