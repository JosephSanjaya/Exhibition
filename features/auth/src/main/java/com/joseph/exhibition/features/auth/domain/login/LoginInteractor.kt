package com.joseph.exhibition.features.auth.domain.login

import com.joseph.exhibition.features.auth.data.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.Session
import javax.inject.Inject

/**
 * Interactor implementation for the use case of user login.
 *
 * @property repo the repository used for user login.
 */
class LoginInteractor @Inject constructor(
    private val repo: AuthRepo
) : LoginUseCase {

    override suspend fun invoke(email: String, password: String): Session {
        return repo.doLogin(email, password)
    }

    /**
     * Dagger module that provides the binding for [LoginUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the [LoginInteractor] implementation of [LoginUseCase].
         *
         * @param impl the [LoginInteractor] instance to bind.
         * @return the bound [LoginUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: LoginInteractor): LoginUseCase
    }
}
