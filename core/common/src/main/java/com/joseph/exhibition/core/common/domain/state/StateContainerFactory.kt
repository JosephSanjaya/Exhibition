package com.joseph.exhibition.core.common.domain.state

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.CoroutineScope

/**
 * A factory interface that creates [StateContainerImpl] instances with the given scope.
 */
@AssistedFactory
interface StateContainerFactory {

    /**
     * Creates a [StateContainerImpl] instance with the given scope.
     * @param scope The coroutine scope to use for the state operations.
     * @return A [StateContainerImpl] instance.
     */
    fun create(@Assisted scope: CoroutineScope): StateContainerImpl
}
