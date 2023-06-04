package com.joseph.exhibition.core.common.domain.maintenance

/**
 * Use case interface for checking if maintenance mode is enabled.
 */
interface MaintenanceCheckUseCase {
    /**
     * Invokes the maintenance mode check and returns a boolean indicating whether maintenance mode is enabled.
     *
     * @return `true` if maintenance mode is enabled, `false` otherwise.
     */
    suspend operator fun invoke(): Boolean
}
