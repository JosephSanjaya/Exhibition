package com.joseph.exhibition.features.auth.domain.jwt

import com.joseph.exhibition.features.auth.data.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.Jwt
import javax.inject.Inject

/**
 * Interactor implementation for the use case of creating a JSON Web Token (JWT).
 *
 * @property repo the repository used for JWT creation.
 */
class CreateJWTInteractor @Inject constructor(
    private val repo: AuthRepo
) : CreateJWTUseCase {

    override suspend fun invoke(): Jwt {
        return repo.createJwtToken()
    }

    /**
     * Dagger module that provides the binding for [CreateJWTUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the [CreateJWTInteractor] implementation of [CreateJWTUseCase].
         *
         * @param impl the [CreateJWTInteractor] instance to bind.
         * @return the bound [CreateJWTUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: CreateJWTInteractor): CreateJWTUseCase
    }
}
