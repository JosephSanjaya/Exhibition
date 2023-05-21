package com.joseph.exhibition.core.common.data.remoteconfig

/**
 * An interface for accessing flag configuration values.
 */
interface FlagConfigRepo {
    /**
     * Checks if the app requires a force update.
     * @return True if the app requires a force update, false otherwise.
     */
    suspend fun isForceUpdate(): Boolean
}
