package com.joseph.exhibition.features.auth.domain.guest

import io.appwrite.models.Session

/**
 * Use case interface for performing a guest login.
 */
interface GuestLoginUseCase {
    /**
     * Invokes the use case to perform a guest login.
     *
     * @return the session after successful guest login.
     */
    suspend operator fun invoke(): Session
}
