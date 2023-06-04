package com.joseph.exhibition.domain.splash

import com.joseph.exhibition.core.common.domain.forceupdate.ForceUpdateCheckUseCase
import com.joseph.exhibition.core.common.domain.forceupdate.ForceUpdateNoteUseCase
import com.joseph.exhibition.core.common.domain.maintenance.MaintenanceCheckUseCase
import com.joseph.exhibition.core.common.domain.maintenance.MaintenanceMessageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Implementation of [SplashLoadingUseCase] that handles the splash loading process.
 *
 * @param maintenanceCheckUseCase The use case for checking maintenance status.
 * @param maintenanceMessageUseCase The use case for retrieving the maintenance message.
 * @param forceUpdateCheckUseCase The use case for checking force update status.
 * @param forceUpdateNoteUseCase The use case for retrieving the force update note.
 */
class SplashLoadingInteractor @Inject constructor(
    private val maintenanceCheckUseCase: MaintenanceCheckUseCase,
    private val maintenanceMessageUseCase: MaintenanceMessageUseCase,
    private val forceUpdateCheckUseCase: ForceUpdateCheckUseCase,
    private val forceUpdateNoteUseCase: ForceUpdateNoteUseCase
) : SplashLoadingUseCase {

    override suspend fun invoke(): Pair<BlockingType?, String?> {
        val blockingType = getBlockingType()
        val message = getMessage(blockingType)
        return blockingType to message
    }

    /**
     * Determines the type of blocking scenario based on the results of the maintenance and force update checks.
     *
     * @return The type of blocking scenario, or null if no blocking scenario is detected.
     */
    private suspend fun getBlockingType(): BlockingType? {
        return when {
            maintenanceCheckUseCase() -> BlockingType.MAINTENANCE
            forceUpdateCheckUseCase() -> BlockingType.FORCE_UPDATE
            else -> null
        }
    }

    /**
     * Retrieves the message associated with the specified blocking type.
     *
     * @param blockingType The type of blocking scenario.
     * @return The message associated with the blocking scenario, or null if the blocking type is not recognized.
     */
    private suspend fun getMessage(blockingType: BlockingType?): String? {
        return when (blockingType) {
            BlockingType.MAINTENANCE -> maintenanceMessageUseCase()
            BlockingType.FORCE_UPDATE -> forceUpdateNoteUseCase()
            else -> null
        }
    }

    /**
     * Dagger module for binding the [SplashLoadingInteractor] implementation to [SplashLoadingUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [SplashLoadingInteractor] to [SplashLoadingUseCase].
         *
         * @param impl The implementation of [SplashLoadingInteractor] to bind.
         * @return The bound [SplashLoadingUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: SplashLoadingInteractor): SplashLoadingUseCase
    }
}
