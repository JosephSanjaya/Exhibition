package com.joseph.exhibition.core.common.data.countries

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.CountryList
import io.appwrite.services.Locale
import javax.inject.Inject

/**
 * A data source implementation for retrieving a list of countries based on the current locale.
 *
 * @property locale the current locale used for country data retrieval.
 */
class CountriesDataSource @Inject constructor(
    private val locale: Locale
) : CountriesRepo {

    override suspend fun getAll(): CountryList {
        return locale.listCountries()
    }

    /**
     * Dagger module that provides the binding for [CountriesRepo].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {
        /**
         * Binds the [CountriesDataSource] implementation of [CountriesRepo].
         *
         * @param impl the [CountriesDataSource] instance to bind.
         * @return the bound [CountriesRepo] instance.
         */
        @Binds
        abstract fun bind(impl: CountriesDataSource): CountriesRepo
    }
}
