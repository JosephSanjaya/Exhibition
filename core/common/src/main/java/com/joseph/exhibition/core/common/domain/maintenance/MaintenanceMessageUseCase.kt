package com.joseph.exhibition.core.common.domain.maintenance

/**
 * Use case interface for retrieving the maintenance message.
 */
interface MaintenanceMessageUseCase {
    /**
     * Invokes the maintenance message retrieval and returns the maintenance message as a [String].
     *
     * @return The maintenance message.
     */
    suspend operator fun invoke(): String
}
