package com.joseph.exhibition.core.common.domain.state

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface StateContainer {
    companion object {
        const val TAG = "StateContainer"
    }

    fun <T> getState(key: String, defaultValue: T): StateFlow<T>
    fun <T> setState(key: String, value: T)
    fun <T> processState(key: String, defaultCurrentState: T, action: suspend () -> T): Job
}