package com.joseph.exhibition.core.common.domain.state

data class State<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String? = null,
)
