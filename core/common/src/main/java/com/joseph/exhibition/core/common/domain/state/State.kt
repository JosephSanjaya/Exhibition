package com.joseph.exhibition.core.common.domain.state

/**
 * A data class that represents the state of a value.
 * @param data The optional data of the state.
 * @param loading The boolean flag that indicates if the state is loading.
 * @param error The optional error of the state.
 */
data class State<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: StateException? = null,
)
