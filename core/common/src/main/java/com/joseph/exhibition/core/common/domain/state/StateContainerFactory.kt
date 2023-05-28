package com.joseph.exhibition.core.common.domain.state

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.CoroutineScope

@AssistedFactory
interface StateContainerFactory {

    fun create(@Assisted scope: CoroutineScope): StateContainerImpl
}
