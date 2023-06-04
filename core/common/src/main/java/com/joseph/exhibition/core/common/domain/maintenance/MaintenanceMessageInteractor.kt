package com.joseph.exhibition.core.common.domain.maintenance

import com.joseph.exhibition.core.common.data.remoteconfig.ConfigRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Implementation of [MaintenanceMessageUseCase] that retrieves the maintenance message.
 *
 * @param repo The repository for retrieving configuration values.
 */
class MaintenanceMessageInteractor @Inject constructor(
    private val repo: ConfigRepo
) : MaintenanceMessageUseCase {

    companion object {
        private const val KEY = "maintenances-notes"
    }

    override suspend fun invoke(): String {
        return repo.getString(KEY)
    }

    /**
     * Dagger module for binding the [MaintenanceMessageInteractor] implementation to [MaintenanceMessageUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [MaintenanceMessageInteractor] to [MaintenanceMessageUseCase].
         *
         * @param impl The implementation of [MaintenanceMessageInteractor] to bind.
         * @return The bound [MaintenanceMessageUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: MaintenanceMessageInteractor): MaintenanceMessageUseCase
    }
}
