package com.joseph.exhibition.core.common.domain.forceupdate

import com.blankj.utilcode.util.AppUtils
import com.joseph.exhibition.core.common.data.remoteconfig.ConfigRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * Implementation of [ForceUpdateCheckUseCase] that checks if a force update is required.
 *
 * @param repo The [ConfigRepo] instance to retrieve configuration values.
 */
class ForceUpdateCheckInteractor @Inject constructor(
    private val repo: ConfigRepo
) : ForceUpdateCheckUseCase {

    companion object {
        private const val KEY = "is-force-update"
        private const val MIN_CODE_KEY = "min-version"
    }

    override suspend fun invoke(): Boolean {
        return repo.getFlag(KEY) && AppUtils.getAppVersionCode() < repo.getInt(MIN_CODE_KEY)
    }

    /**
     * Dagger module for binding the [ForceUpdateCheckInteractor] implementation to [ForceUpdateCheckUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [ForceUpdateCheckInteractor] to [ForceUpdateCheckUseCase].
         *
         * @param impl The implementation of [ForceUpdateCheckInteractor] to bind.
         * @return The bound [ForceUpdateCheckUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: ForceUpdateCheckInteractor): ForceUpdateCheckUseCase
    }
}
