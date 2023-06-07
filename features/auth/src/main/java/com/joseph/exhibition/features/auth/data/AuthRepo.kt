package com.joseph.exhibition.features.auth.data

import io.appwrite.models.Jwt
import io.appwrite.models.Session

/**
 * Repository interface for handling authentication-related operations.
 */
interface AuthRepo {
    /**
     * Performs user registration with the provided registration payload.
     *
     * @param payload the registration payload containing user information.
     * @return the user session after successful registration.
     */
    suspend fun doRegister(payload: RegisterPayload): Session

    /**
     * Performs user login with the provided email and password.
     *
     * @param email the user's email.
     * @param password the user's password.
     * @return the user session after successful login.
     */
    suspend fun doLogin(email: String, password: String): Session

    /**
     * Performs anonymous login, generating a session for anonymous user access.
     *
     * @return the session for anonymous user access.
     */
    suspend fun doAnonymLogin(): Session

    /**
     * Creates a JWT (JSON Web Token) for authentication purposes.
     *
     * @return the created JWT.
     */
    suspend fun createJwtToken(): Jwt
}
