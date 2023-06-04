package com.joseph.exhibition.core.common.domain.forceupdate

import com.joseph.exhibition.core.common.data.remoteconfig.ConfigRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Implementation of [ForceUpdateNoteUseCase] that retrieves the force update note.
 *
 * @param repo The repository for retrieving configuration values.
 */
class ForceUpdateNoteInteractor @Inject constructor(
    private val repo: ConfigRepo
) : ForceUpdateNoteUseCase {

    companion object {
        private const val KEY = "update-notes"
    }

    override suspend fun invoke(): String {
        return repo.getString(KEY)
    }

    /**
     * Dagger module for binding the [ForceUpdateNoteInteractor] implementation to [ForceUpdateNoteUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [ForceUpdateNoteInteractor] to [ForceUpdateNoteUseCase].
         *
         * @param impl The implementation of [ForceUpdateNoteInteractor] to bind.
         * @return The bound [ForceUpdateNoteUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: ForceUpdateNoteInteractor): ForceUpdateNoteUseCase
    }
}
