package com.joseph.exhibition.features.auth.domain.guest

import com.joseph.exhibition.features.auth.data.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.Session
import javax.inject.Inject

/**
 * Interactor implementation for the use case of performing a guest login.
 *
 * @property repo the repository used for guest login.
 */
class GuestLoginInteractor @Inject constructor(
    private val repo: AuthRepo
) : GuestLoginUseCase {

    override suspend fun invoke(): Session {
        return repo.doAnonymLogin()
    }

    /**
     * Dagger module that provides the binding for [GuestLoginUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the [GuestLoginInteractor] implementation of [GuestLoginUseCase].
         *
         * @param impl the [GuestLoginInteractor] instance to bind.
         * @return the bound [GuestLoginUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: GuestLoginInteractor): GuestLoginUseCase
    }
}
