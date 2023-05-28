package com.joseph.exhibition.core.common.domain.state

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

/**
 * A container that manages the state of different keys using [StateFlow].
 */
interface StateContainer {
    /**
     * A companion object that holds some constants for the [StateContainer].
     */
    companion object {
        /**
         * The tag for logging purposes.
         */
        const val TAG = "StateContainer"

        /**
         * The code for timeout error.
         */
        const val TIMEOUT_CODE = 408

        /**
         * The code for unknown error.
         */
        const val UNKNOWN_CODE = 500
    }

    /**
     * Returns the [StateFlow] of the given key with the given default value if the key is not found.
     * @param key The key to look up the state.
     * @param defaultValue The default value to use if the key is not found.
     * @return The [StateFlow] of the key or the default value.
     */
    fun <T> getState(key: String, defaultValue: T): StateFlow<T>

    /**
     * Sets the state of the given key to the given value.
     * @param key The key to update the state.
     * @param value The value to set the state to.
     */
    fun <T> setState(key: String, value: T)

    /**
     * Processes the state of the given key by applying an action on the current state and updating it with the result.
     * @param key The key to process the state.
     * @param defaultCurrentState The default current state to use if the key is not found.
     * @param action The action to apply on the current state. It should return a new state value.
     * @return A [Job] that represents the asynchronous operation.
     */
    fun <T> processState(key: String, defaultCurrentState: T, action: suspend () -> T): Job
}
