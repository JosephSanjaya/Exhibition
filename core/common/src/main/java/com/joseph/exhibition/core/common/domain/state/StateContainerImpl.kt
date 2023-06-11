package com.joseph.exhibition.core.common.domain.state

import android.util.Log
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.appwrite.exceptions.AppwriteException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

/**
 * An implementation of the [StateContainer] interface using [ConcurrentHashMap] and [MutableStateFlow].
 * @param dispatcher The coroutine context to use for the state operations.
 * @param scope The coroutine scope to use for the state operations.
 */
class StateContainerImpl @AssistedInject constructor(
    @Named(StateContainer.TAG) private val dispatcher: CoroutineContext,
    @Assisted private val scope: CoroutineScope,
) : StateContainer {

    /**
     * A concurrent hash map that stores the state flows for different keys.
     */
    private val stateMap: ConcurrentHashMap<String, StateFlow<*>> = ConcurrentHashMap()

    /**
     * A lock object to synchronize the state operations.
     */
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
            val stateFlow = stateMap[key] as? MutableStateFlow<T> ?: createStateFlow(value).also {
                stateMap[key] = it
            }
            stateFlow.value = value
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> processState(
        key: String,
        defaultCurrentState: T,
        action: suspend () -> T,
    ): Job {
        val currentState = getState(key, defaultCurrentState).value as State<T>
        val loadingState = currentState.copy(isLoading = true, isFinished = false, error = null)
        return scope.launch(dispatcher) {
            val newState = try {
                val data = action()
                loadingState.copy(data = data, isLoading = false, isFinished = true)
            } catch (e: AppwriteException) {
                Log.e(AppwriteException::class.simpleName, e.response ?: e.message.toString())
                handleAppWriteError(e, loadingState)
            } catch (e: SocketTimeoutException) {
                Log.e(SocketTimeoutException::class.simpleName, e.message.toString())
                handleTimeoutError(e, loadingState)
            } catch (e: UnknownHostException) {
                Log.e(UnknownHostException::class.simpleName, e.message.toString())
                handleNetworkError(e, loadingState)
            } catch (e: Exception) {
                Log.e(Exception::class.simpleName, e.message.toString())
                handleUnknownError(e, loadingState)
            }
            setState(key, newState)
        }
    }

    /**
     * Handles a timeout error by creating a new state with the error and loading fields updated.
     * @param e The socket timeout exception that occurred.
     * @param currentState The current state of the key.
     * @return A new state with the error and loading fields updated.
     */
    private fun <T> handleTimeoutError(
        e: SocketTimeoutException,
        currentState: State<T>
    ): State<T> {
        return currentState.copy(
            error = StateException(
                e.message ?: "Unknown Error",
                StateExceptionType.TIMEOUT,
                StateContainer.TIMEOUT_CODE
            ),
            isLoading = false,
            isFinished = true
        )
    }

    /**
     * Handles a network error by creating a new state with the error and loading fields updated.
     * @param e The unknown host exception that occurred.
     * @param currentState The current state of the key.
     * @return A new state with the error and loading fields updated.
     */
    private fun <T> handleNetworkError(e: UnknownHostException, currentState: State<T>): State<T> {
        return currentState.copy(
            error = StateException(
                e.message ?: "Unknown Error",
                StateExceptionType.NETWORK,
                StateContainer.TIMEOUT_CODE
            ),
            isLoading = false,
            isFinished = true
        )
    }

    /**
     * Handles an appwrite error by creating a new state with the error and loading fields updated.
     * @param e The appwrite exception that occurred.
     * @param currentState The current state of the key.
     * @return A new state with the error and loading fields updated.
     */
    private fun <T> handleAppWriteError(e: AppwriteException, currentState: State<T>): State<T> {
        return currentState.copy(
            error = StateException(
                e.message ?: "Unknown Error",
                StateExceptionType.APPWRITE,
                e.code,
                e.response
            ),
            isLoading = false,
            isFinished = true
        )
    }

    /**
     * Handles an unknown error by creating a new state with the error and loading fields updated.
     * @param e The throwable that occurred.
     * @param currentState The current state of the key.
     * @return A new state with the error and loading fields updated.
     */
    private fun <T> handleUnknownError(e: Throwable, currentState: State<T>): State<T> {
        return currentState.copy(
            error = StateException(
                e.message ?: "Unknown Error",
                StateExceptionType.UNKNOWN,
                StateContainer.UNKNOWN_CODE
            ),
            isLoading = false,
            isFinished = true
        )
    }

    /**
     * Creates a [MutableStateFlow] with the given default value.
     * @param defaultValue The default value to use for the state flow.
     * @return A [MutableStateFlow] with the default value.
     */
    private fun <T> createStateFlow(defaultValue: T): MutableStateFlow<T> {
        return MutableStateFlow(defaultValue)
    }
}
