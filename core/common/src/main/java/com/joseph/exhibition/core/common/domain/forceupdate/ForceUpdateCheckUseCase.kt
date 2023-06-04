package com.joseph.exhibition.core.common.domain.forceupdate

/**
 * Use case interface for checking if a force update is required.
 */
interface ForceUpdateCheckUseCase {
    /**
     * Invokes the force update check and returns a boolean indicating whether a force update is required.
     *
     * @return `true` if a force update is required, `false` otherwise.
     */
    suspend operator fun invoke(): Boolean
}
