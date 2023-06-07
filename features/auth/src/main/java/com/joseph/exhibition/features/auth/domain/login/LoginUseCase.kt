package com.joseph.exhibition.features.auth.domain.login

import io.appwrite.models.Session

/**
 * Use case interface for performing user login.
 */
interface LoginUseCase {
    /**
     * Invokes the use case to perform user login with the provided email and password.
     *
     * @param email the user's email.
     * @param password the user's password.
     * @return the session after successful login.
     */
    suspend operator fun invoke(email: String, password: String): Session
}
