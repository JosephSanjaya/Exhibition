package com.joseph.exhibition.features.auth.domain.jwt

import io.appwrite.models.Jwt

/**
 * Use case interface for creating a JSON Web Token (JWT).
 */
interface CreateJWTUseCase {
    /**
     * Invokes the use case to create a JSON Web Token (JWT).
     *
     * @return the created JSON Web Token (JWT).
     */
    suspend operator fun invoke(): Jwt
}
