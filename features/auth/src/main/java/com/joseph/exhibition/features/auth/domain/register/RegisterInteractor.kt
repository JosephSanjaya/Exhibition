package com.joseph.exhibition.features.auth.domain.register

import com.joseph.exhibition.features.auth.data.AuthRepo
import com.joseph.exhibition.features.auth.data.RegisterPayload
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.Session
import javax.inject.Inject

/**
 * Interactor implementation for the use case of user registration.
 *
 * @property repo the repository used for user registration.
 */
class RegisterInteractor @Inject constructor(
    private val repo: AuthRepo
) : RegisterUseCase {

    override suspend fun invoke(payload: RegisterPayload): Session {
        return repo.doRegister(payload)
    }

    /**
     * Dagger module that provides the binding for [RegisterUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the [RegisterInteractor] implementation of [RegisterUseCase].
         *
         * @param impl the [RegisterInteractor] instance to bind.
         * @return the bound [RegisterUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: RegisterInteractor): RegisterUseCase
    }
}
