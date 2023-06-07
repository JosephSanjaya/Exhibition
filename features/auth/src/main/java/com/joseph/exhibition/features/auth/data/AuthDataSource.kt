package com.joseph.exhibition.features.auth.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.Jwt
import io.appwrite.models.Session
import io.appwrite.services.Account
import javax.inject.Inject

/**
 * Data source implementation for handling authentication-related operations.
 *
 * @property account the account instance used for authentication.
 */
class AuthDataSource @Inject constructor(
    private val account: Account
) : AuthRepo {

    override suspend fun doRegister(payload: RegisterPayload): Session {
        account.create(
            payload.tagline,
            payload.email,
            payload.password,
            payload.username
        )
        return account.createEmailSession(payload.email, payload.password).also {
            account.updatePrefs(payload.getPayloadMap())
        }
    }

    override suspend fun doLogin(email: String, password: String): Session {
        return account.createEmailSession(email, password)
    }

    override suspend fun doAnonymLogin(): Session {
        return account.createAnonymousSession()
    }

    override suspend fun createJwtToken(): Jwt {
        return account.createJWT()
    }

    /**
     * Dagger module that provides the binding for [AuthRepo].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the [AuthDataSource] implementation of [AuthRepo].
         *
         * @param impl the [AuthDataSource] instance to bind.
         * @return the bound [AuthRepo] instance.
         */
        @Binds
        abstract fun bind(impl: AuthDataSource): AuthRepo
    }
}
