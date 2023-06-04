package com.joseph.exhibition.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.exhibition.core.common.domain.state.State
import com.joseph.exhibition.core.common.domain.state.StateContainer
import com.joseph.exhibition.core.common.domain.state.StateContainerFactory
import com.joseph.exhibition.core.common.domain.state.state
import com.joseph.exhibition.domain.splash.BlockingType
import com.joseph.exhibition.domain.splash.SplashLoadingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for handling the splash loading process.
 *
 * @param stateFactory The factory for creating the StateContainer.
 * @param useCase The use case for performing the splash loading logic.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    stateFactory: StateContainerFactory,
    private val useCase: SplashLoadingUseCase
) : ViewModel() {

    companion object {
        private const val STATE_KEY = "splash-loading"
    }

    private val states: StateContainer = stateFactory.create(viewModelScope)

    /**
     * The state that represents the result of the splash loading process.
     */
    val splashState by states.state<State<Pair<BlockingType?, String?>>>(
        STATE_KEY,
        State(null to null)
    )

    /**
     * Initiates the splash loading process.
     */
    fun doLoading() {
        states.processState(
            STATE_KEY,
            State<Pair<BlockingType?, String?>>(null to null)
        ) { useCase() }
    }
}
