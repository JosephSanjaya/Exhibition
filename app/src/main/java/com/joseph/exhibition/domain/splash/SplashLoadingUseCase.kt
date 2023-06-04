package com.joseph.exhibition.domain.splash

/**
 * Use case interface for handling the splash loading process.
 */
interface SplashLoadingUseCase {
    /**
     * Invokes the splash loading process and returns a pair of [BlockingType] and [String].
     *
     * The [BlockingType] represents the type of blocking scenario, such as maintenance or force update.
     * The [String] represents additional information related to the blocking scenario, such as a maintenance message.
     *
     * @return A pair of [BlockingType] and [String], or null if no blocking scenario is present.
     */
    suspend operator fun invoke(): Pair<BlockingType?, String?>
}
