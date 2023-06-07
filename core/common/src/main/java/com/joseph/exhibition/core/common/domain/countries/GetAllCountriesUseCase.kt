package com.joseph.exhibition.core.common.domain.countries

import io.appwrite.models.CountryList

/**
 * Use case interface for retrieving a list of all countries.
 */
interface GetAllCountriesUseCase {
    /**
     * Invokes the use case to retrieve a list of all countries.
     *
     * @return the list of countries.
     */
    suspend operator fun invoke(): CountryList
}
