package com.joseph.exhibition.core.common.domain.maintenance

import com.joseph.exhibition.core.common.data.remoteconfig.ConfigRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Implementation of [MaintenanceCheckUseCase] that checks if maintenance mode is enabled.
 *
 * @param repo The repository for retrieving configuration values.
 */
class MaintenanceCheckInteractor @Inject constructor(
    private val repo: ConfigRepo
) : MaintenanceCheckUseCase {

    companion object {
        private const val KEY = "is-maintenaces"
    }

    override suspend fun invoke(): Boolean {
        return repo.getFlag(KEY)
    }

    /**
     * Dagger module for binding the [MaintenanceCheckInteractor] implementation to [MaintenanceCheckUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [MaintenanceCheckInteractor] to [MaintenanceCheckUseCase].
         *
         * @param impl The implementation of [MaintenanceCheckInteractor] to bind.
         * @return The bound [MaintenanceCheckUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: MaintenanceCheckInteractor): MaintenanceCheckUseCase
    }
}
