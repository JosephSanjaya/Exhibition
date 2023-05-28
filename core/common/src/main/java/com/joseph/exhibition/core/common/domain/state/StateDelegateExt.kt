package com.joseph.exhibition.core.common.domain.state

import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty

inline fun <reified T> StateContainer.state(
    key: String,
    defaultValue: T,
): ReadOnlyProperty<Any, StateFlow<T>> {
    val stateFlow = getState(key, defaultValue)
    return ReadOnlyProperty { _, _ -> stateFlow }
}