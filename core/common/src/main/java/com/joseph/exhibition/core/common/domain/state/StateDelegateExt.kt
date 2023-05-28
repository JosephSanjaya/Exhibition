package com.joseph.exhibition.core.common.domain.state

import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty

/**
 * An inline function that returns a read-only property of a state flow for the given key and default value.
 * @param key The key to look up the state flow.
 * @param defaultValue The default value to use if the key is not found.
 * @return A read-only property of a state flow for the key or the default value.
 */
inline fun <reified T> StateContainer.state(
    key: String,
    defaultValue: T,
): ReadOnlyProperty<Any, StateFlow<T>> {
    val stateFlow = getState(key, defaultValue)
    return ReadOnlyProperty { _, _ -> stateFlow }
}
