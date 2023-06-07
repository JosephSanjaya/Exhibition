package com.joseph.exhibition.features.auth.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainer
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.state
import com.joseph.exhibition.features.auth.data.RegisterPayload
import com.joseph.exhibition.features.auth.domain.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appwrite.models.Session
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 * A [ViewModel] that handles the test logic and state management.
 *
 * @param stateFactory The factory that creates the [StateContainer] for this [ViewModel].
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    stateFactory: StateContainerFactory,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    companion object {
        private const val REGISTER_JOB_KEY = "register"
    }

    // The container that holds the states for this [ViewModel].
    private val states: StateContainer = stateFactory.create(viewModelScope)

    // The state that represents the test result.
    val registerState by states.state(REGISTER_JOB_KEY, State(Session.from(mapOf())))

    // The job that performs the test logic.
    private var _registerJob: Job? = null
    val registerJob get() = _registerJob

    /**
     * Executes the test logic based on the given [intent].
     *
     * @param intent The [TestIntent] that specifies which test to perform.
     */
    fun doRegister(payload: RegisterPayload) {
        _registerJob = states.processState(REGISTER_JOB_KEY, State(Session.from(mapOf()))) {
            registerUseCase(payload)
        }
    }
}
