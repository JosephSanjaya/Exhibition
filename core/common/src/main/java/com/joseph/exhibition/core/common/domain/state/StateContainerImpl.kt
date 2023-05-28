package com.joseph.exhibition.core.common.domain.state

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class StateContainerImpl @AssistedInject constructor(
    @Named(StateContainer.TAG) private val dispatcher: CoroutineContext,
    @Assisted private val scope: CoroutineScope,
) : StateContainer {

    private val stateMap: ConcurrentHashMap<String, StateFlow<*>> = ConcurrentHashMap()
    private val lock: Any = Any()

    @Suppress("UNCHECKED_CAST")
    override fun <T> getState(key: String, defaultValue: T): StateFlow<T> {
        synchronized(lock) {
            return stateMap[key]?.let { it as StateFlow<T> } ?: createStateFlow(defaultValue).also {
                stateMap[key] = it as MutableStateFlow<*>
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> setState(key: String, value: T) {
        synchronized(lock) {
            val stateFlow = stateMap[key] as? MutableStateFlow<T>
            stateFlow?.value = value
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> processState(
        key: String,
        defaultCurrentState: T,
        action: suspend () -> T,
    ): Job {
        val currentState = getState(key, defaultCurrentState).value as State<T>
        val loadingState = currentState.copy(loading = true, error = null)
        return scope.launch(dispatcher) {
            try {
                val data = action()
                val successState = loadingState.copy(data = data, loading = false)
                setState(key, successState)
            } catch (e: Throwable) {
                val errorState = loadingState.copy(error = e.message, loading = false)
                setState(key, errorState)
            }
        }
    }

    private fun <T> createStateFlow(defaultValue: T): MutableStateFlow<T> {
        return MutableStateFlow(defaultValue)
    }
}