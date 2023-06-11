package com.joseph.exhibition.features.auth.domain.register

import io.appwrite.models.Session

/**
 * Use case interface for performing user registration.
 */
interface RegisterUseCase {
    /**
     * Invokes the use case to perform user registration with the provided payload.
     *
     * @param payload the registration payload containing user details.
     * @return the session after successful registration.
     */
    suspend operator fun invoke(payload: RegisterPayload): Session
}
