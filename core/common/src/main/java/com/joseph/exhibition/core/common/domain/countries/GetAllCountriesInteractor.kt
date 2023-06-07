package com.joseph.exhibition.core.common.domain.countries

import com.joseph.exhibition.core.common.data.countries.CountriesRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.models.CountryList
import javax.inject.Inject

/**
 * Interactor implementation for the use case of retrieving a list of all countries.
 *
 * @property repo the repository used for retrieving the country list.
 */
class GetAllCountriesInteractor @Inject constructor(
    private val repo: CountriesRepo
) : GetAllCountriesUseCase {

    override suspend fun invoke(): CountryList {
        return repo.getAll()
    }

    /**
     * Dagger module that provides the binding for [GetAllCountriesUseCase].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {
        /**
         * Binds the [GetAllCountriesInteractor] implementation of [GetAllCountriesUseCase].
         *
         * @param impl the [GetAllCountriesInteractor] instance to bind.
         * @return the bound [GetAllCountriesUseCase] instance.
         */
        @Binds
        abstract fun bind(impl: GetAllCountriesInteractor): GetAllCountriesUseCase
    }
}
